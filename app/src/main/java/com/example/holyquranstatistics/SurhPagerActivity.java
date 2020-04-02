package com.example.holyquranstatistics;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.holyquranstatistics.databinding.ActivitySurhPagerBinding;
import com.google.android.material.tabs.TabLayout;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SurhPagerActivity extends AppCompatActivity  {


    public static final String EXTRA_ID = "SID";
    public static final String EXTRA_SURH_ID = "SURH_ID";
    public static final String EXTRA_SURH_NAME = "COW";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";
    public static final String EXTRA_AYH_START = "FROM";
    public static final String EXTRA_SURH_PAGE_END = "END";
    public static final String EXTRA_AYHT = "FROM" ;
;
    private ViewPager2 mViewPager;

    private ViewPager2.OnPageChangeCallback mPageChangeCallback;
    private List<Quran> mQurans;
    public Quran quran ;

    private UUID SID ;

    Toolbar mainToolbar ;

    private Boolean isActionBarHidden = false ;
    private ActivitySurhPagerBinding mBinding ;


    public static final int MSG_HIDE_ACTIONBAR = 1;
    private static final long DEFAULT_HIDE_AFTER_TIME = 2000;
    private final PagerHandler handler = new PagerHandler(this);
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



    private static class PagerHandler extends Handler {
        private final WeakReference<SurhPagerActivity> activity;
        PagerHandler(SurhPagerActivity activity) {
            this.activity = new WeakReference<>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            SurhPagerActivity activity = this.activity.get();
            if (activity != null) {
                if (msg.what == MSG_HIDE_ACTIONBAR) {
                    activity.appbat() ;
                } else {
                    super.handleMessage(msg);
                }
            }
        }

    }


    /*
        We apply this code for Binding Activity
        https://stackoverflow.com/questions/52346350/databinding-activitymainbinding-unable-to-be-imported-in-android-project

        https://www.androidhive.info/2020/01/viewpager2-pager-transformations-intro-slider-pager-animations-pager-transformations/

        https://github.com/ravi8x/ViewPager2-Examples

        https://www.raywenderlich.com/8192680-viewpager2-in-android-getting-started

     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySurhPagerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
     //   setContentView(R.layout.activity_surh_pager);

        mainToolbar = findViewById(R.id.qourn_toolbar) ;

        setSupportActionBar(mainToolbar);

        SID = (UUID) getIntent().getSerializableExtra(EXTRA_ID);

        mViewPager = findViewById(R.id.surh_view_pager);

        mQurans = QuranFahras.get(this).getFahras();
        mBinding.surhViewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                quran = mQurans.get(position);
                return QuranFragment.newInstance(quran.getId());
            }

            @Override
            public int getItemCount() {
                return mQurans.size();
            }
        });

        mBinding.surhViewPager.registerOnPageChangeCallback(pageChangeCallback);



/*

        mViewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                 quran = mQurans.get(position);
                return QuranFragment.newInstance(quran.getId());
            }

            @Override
            public int getItemCount() {
                return mQurans.size();
            }


        });

        mPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Toast.makeText(SurhPagerActivity.this,  " position= "  +position , Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        };*/



        for (int i = 0; i < mQurans.size(); i++) {
            if (mQurans.get(i).getId().equals(SID) ){
                mViewPager.setCurrentItem(i);

                break;
            }
        }






    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            handler.sendEmptyMessageDelayed(MSG_HIDE_ACTIONBAR, DEFAULT_HIDE_AFTER_TIME);
        } else {
            handler.removeMessages(MSG_HIDE_ACTIONBAR);
        }
    }


    ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        //    Toast.makeText(SurhPagerActivity.this,  " position= "  +position , Toast.LENGTH_SHORT).show();

             mBinding.qournToolbar.setTitle("سورة "+mQurans.get(position).getSurhName());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };








    void appbat()
    {

        if(isActionBarHidden)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mainToolbar.setVisibility(View.VISIBLE);
            isActionBarHidden = false;
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            mainToolbar.setVisibility(View.GONE);
            isActionBarHidden = true;

        }



    }
}
