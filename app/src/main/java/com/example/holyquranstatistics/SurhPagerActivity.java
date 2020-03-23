package com.example.holyquranstatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SurhPagerActivity extends AppCompatActivity {


    public static final String EXTRA_ID = "SID";
    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";
    public static final String EXTRA_AYH_START = "FROM";
    public static final String EXTRA_SURH_PAGE_END = "END";
    public static final String EXTRA_AYHT = "FROM" ;

    private ViewPager mViewPager;
    private List<Quran> mQurans;

    FragmentManager fragmentManager ;
    UUID SID ;

    public static Intent newIntent(Context packageContext, UUID Id, String surhID , String surhName, String surhayhNumbers , int startSurhFrom , int surhEnd) {

        Intent intent = new Intent(packageContext, SurhPagerActivity.class);
      //  Bundle bundle = new Bundle();
        intent.putExtra(EXTRA_ID, Id);
        intent.putExtra(EXTRA_SURH_ID, surhID);
        intent.putExtra(EXTRA_SURH_NAME, surhName);
        intent.putExtra(EXTRA_AYHT_COUNT, surhayhNumbers);
        intent.putExtra(EXTRA_AYH_START, startSurhFrom);
        intent.putExtra(EXTRA_SURH_PAGE_END , surhEnd);

        return intent;
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surh_pager);

        SID = (UUID) getIntent().getSerializableExtra(EXTRA_ID);

        mViewPager = (ViewPager) findViewById(R.id.surh_view_pager);

        mQurans = QuranFahras.get(this).getFahras();

        fragmentManager = getSupportFragmentManager();


        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {


            @Override
            public int getCount() {
                return mQurans.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                Quran quran = mQurans.get(position);
                return QuranFragment.newInstance(quran.getId());
            }
        });




        for (int i = 0; i < mQurans.size(); i++) {
            if (mQurans.get(i).getId().equals(SID) ){
                mViewPager.setCurrentItem(i);

                break;
            }
        }



    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
