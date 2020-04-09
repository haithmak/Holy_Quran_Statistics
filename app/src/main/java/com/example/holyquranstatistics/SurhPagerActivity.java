package com.example.holyquranstatistics;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.adapters.TextViewBindingAdapter;
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
    public static final String EXTRA_SURH_START_PAGE = "START_PAGE";
    public static final String EXTRA_AYHT_COUNT = "AYHT_COUNT";

    public static final String EXTRA_AYH_START = "FROM";
    public static final String EXTRA_SURH_PAGE_END = "END";
    public static final String EXTRA_AYHT = "FROM" ;
;
    private ViewPager2 mViewPager;

    private ViewPager2.OnPageChangeCallback mPageChangeCallback;

    private List<Quran> mQurans =new ArrayList<>();
    public Quran quran ;

    private UUID SID ;

    Toolbar mainToolbar ;

    public boolean isActionBarHidden = true ;

    private ActivitySurhPagerBinding mBinding ;
    ActionBarStatus mActionBarStatus = new ActionBarStatus();


    public static final int MSG_HIDE_ACTIONBAR = 1;
    private static final long DEFAULT_HIDE_AFTER_TIME = 2000;
    private final PagerHandler handler = new PagerHandler(this);


    public static Intent newIntent(Context packageContext, UUID Id, String surhID , String surhName , int surhPageStart, int surhayhNumbers , int startSurhFrom , int surhEnd) {

        Intent intent = new Intent(packageContext, SurhPagerActivity.class);
      //  Bundle bundle = new Bundle();
        intent.putExtra(EXTRA_ID, Id);
        intent.putExtra(EXTRA_SURH_ID, surhID);
        intent.putExtra(EXTRA_SURH_NAME, surhName);
        intent.putExtra(EXTRA_SURH_START_PAGE, surhPageStart) ;
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
                //    activity.toggleActionBar() ;

                   activity.toggleActionBarVisibility(false);
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

        SID = (UUID) getIntent().getSerializableExtra(EXTRA_ID);

        mQurans =  QueryUtilsList.get(this).getAllPages();

        mBinding = ActivitySurhPagerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


     //   setContentView(R.layout.activity_surh_pager);

        mainToolbar = findViewById(R.id.qourn_toolbar) ;

        setSupportActionBar(mainToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



       // isActionBarHidden = false;
        mActionBarStatus.setActionBarStatus(false);


        int p = (int)getIntent().getSerializableExtra(EXTRA_SURH_START_PAGE) ;




       // QueryUtilsList queryUtilsList = QueryUtilsList.get(this);



        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        mBinding.surhViewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {

                 //quran = mQurans.get(position);

                // return QuranFragment.newInstance(quran.getId());
                return QuranFragment.arrayList.get(position);

            }

            @Override
            public int getItemCount() {
                //return mQurans.size();

                return QuranFragment.arrayList.size();
            }
        });

        mBinding.surhViewPager.registerOnPageChangeCallback(pageChangeCallback);
         mBinding.surhViewPager.setCurrentItem(p-1, false) ;




//   mViewPager = findViewById(R.id.surh_view_pager);
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
          // Toast.makeText(SurhPagerActivity.this,  " position= "  +mBinding.qournToolbar.isShown() , Toast.LENGTH_SHORT).show();

           mBinding.qournToolbar.setTitle("سورة "+mQurans.get(position).getSurhName());
           mBinding.qournToolbar.setSubtitle("صفحة رقم  "+mQurans.get(position).getSurhPageNumber());
           toggleActionBarVisibility(true);


        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };






      private void animateToolBar(boolean visible) {
       isActionBarHidden = !visible;
          mBinding.actionBarSourh.animate()
                  .translationY(!visible ? 0 : -mBinding.actionBarSourh.getHeight())
                  .setDuration(800)
                  .start();
          if(visible){

          }

    }







    public void toggleActionBarVisibility(boolean visible) {
       if (visible == mActionBarStatus.isActionBarStatus()) {
            toggleActionBar();
         //   animateToolBar(visible);
        }
    }




    public void toggleActionBar( )
    {

        if(mActionBarStatus.isActionBarStatus())
        {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mBinding.qournToolbar.setVisibility(View.VISIBLE);

            /*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
               // setUiVisibility(true);
            }
            else {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                mainToolbar.setVisibility(View.VISIBLE);

            }*/
            mActionBarStatus.setActionBarStatus(false);
        }
        else
        {
            handler.removeMessages(MSG_HIDE_ACTIONBAR);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            mBinding.qournToolbar.setVisibility(View.GONE);
            /*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setUiVisibility(false);
            }
            else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                mainToolbar.setVisibility(View.GONE);
            }*/

            mActionBarStatus.setActionBarStatus(true);
        }




    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quran_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        /*
        Spinner spinner = (Spinner) item.getActionView();

        ArrayList<String> arrayList = new ArrayList<>() ;
        arrayList.add("الانتقال السريع");
        for(int i=0 ; i < mQurans.size();i++)
        {
            arrayList.add(mQurans.get(i).getSurhName()) ;
        }


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinner.setPopupBackgroundResource(R.color.Blue300);
        }*/
        //    spinner.setSelection(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.s :
                return true ;


            case R.id.j :
                return true ;

        }
        return super.onOptionsItemSelected(item);
    }
}
