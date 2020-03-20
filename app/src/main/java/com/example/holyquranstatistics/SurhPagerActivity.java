package com.example.holyquranstatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class SurhPagerActivity extends AppCompatActivity {



    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";
    public static final String EXTRA_AYH_START = "FROM";

    private ViewPager mViewPager;
    private List<Quran> mQurans;

    public static Intent newIntent(Context packageContext, String surhID , String surhName,  String surhayhNumbers , String startSurhFrom) {

        Intent intent = new Intent(packageContext, SurhPagerActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(EXTRA_SURH_ID, surhID);
        bundle.putString(EXTRA_SURH_NAME, surhName);
        bundle.putString(EXTRA_AYHT_COUNT, surhayhNumbers);

        bundle.putString(EXTRA_AYH_START, startSurhFrom);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surh_pager);

        String SID = (String) getIntent().getSerializableExtra(EXTRA_SURH_ID);

        mViewPager = (ViewPager) findViewById(R.id.surh_view_pager);

        mQurans = QuranFahras.get(this).getFahras();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @NonNull
            @Override
            public Fragment getItem(int position) {

                Quran quran = mQurans.get(position);
                return QuranFragment.newInstance(quran.getSurhNumber());
            }

            @Override
            public int getCount() {
                return mQurans.size();
            }
        });

        for (int i = 0; i < mQurans.size(); i++) {
            if (mQurans.get(i).getSurhNumber().equals(SID)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }





    }
}
