package com.example.holyquranstatistics;

import androidx.fragment.app.Fragment;

public class QuranListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new QuranListFragment();
    }
}
