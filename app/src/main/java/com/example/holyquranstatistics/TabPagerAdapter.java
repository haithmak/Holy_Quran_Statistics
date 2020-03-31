package com.example.holyquranstatistics;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter  extends FragmentStateAdapter {


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public TabPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0 :
                return new QuranListFragment() ;

            case 1 :
                return new JoumalListFragment() ;

            default:
                return null ;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
