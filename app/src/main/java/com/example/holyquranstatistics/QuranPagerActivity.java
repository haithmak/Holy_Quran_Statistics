package com.example.holyquranstatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class QuranPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Quran> mQurans;

    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";

    public static final String EXTRA_AYH_START = "FROM";

    private static final String ARG_SURH_ID = "SURH_ID_";
    int surhID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_pager);

         surhID = (int) getIntent().getSerializableExtra(ARG_SURH_ID);

/*

        mViewPager = (ViewPager) findViewById(R.id.quran_view_pager);
        mQurans = QuranFahras.get(this).getFahras();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Quran quran = mQurans.get(position);
                return QuranFragment.newInstance(Integer.parseInt(quran.getSurhNumber()));
            }

            @Override
            public int getCount() {
                return mQurans.size();
            }
        });*/

        for (int i = 0; i < mQurans.size(); i++) {
            if (mQurans.get(i).getSurhNumber().equals(surhID))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, int surhID , String surhName, String surhayhNumbers , int startSurhFrom) {

        Intent intent = new Intent(packageContext, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SURH_ID, surhID);
        bundle.putString(EXTRA_SURH_NAME, surhName);
        bundle.putString(EXTRA_AYHT_COUNT, surhayhNumbers);
        bundle.putInt(EXTRA_AYH_START, startSurhFrom);
        intent.putExtras(bundle);


        return intent;
    }

}
