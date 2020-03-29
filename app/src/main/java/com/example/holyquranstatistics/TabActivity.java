package com.example.holyquranstatistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabActivity extends AppCompatActivity {
    private TabLayout tabLayout ;

    private ViewPager2 tabviewPager ;
   // private ViewPager tabviewPager ;
    private TabPagerAdapter mTabPagerAdapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabviewPager =  findViewById(R.id.tab_view_pager);
        tabviewPager.setAdapter(new TabPagerAdapter(this) );

        tabLayout  = (TabLayout) findViewById(R.id.tablayout_id) ;


        TabLayoutMediator tabLayoutMediator =new TabLayoutMediator(
                tabLayout, tabviewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position)
                {
                    case 0 :
                           tab.setText("فهرس القرآن");
                           tab.setIcon(R.drawable.circle) ;
                    break;
                    case 1 :
                          tab.setText("فهرس الجُمَّل");
                        break;

                }

            }
        }) ;
        tabLayoutMediator.attach();
        /*
        tabLayout  = (TabLayout) findViewById(R.id.tablayout_id) ;
        tabviewPager = (ViewPager) findViewById(R.id.tab_view_pager  );
        mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager()  );

        mTabPagerAdapter.AddFragment( new QuranListFragment() ,"Calculator"  );
        mTabPagerAdapter.AddFragment( new JoumalListFragment() ,"Brochures"  );
       // mTabPagerAdapter.AddFragment( new Fragmentabout() ,"About us"  );
        tabviewPager.setAdapter( mTabPagerAdapter );
        tabLayout.setupWithViewPager(tabviewPager) ;
        */



    }
}
