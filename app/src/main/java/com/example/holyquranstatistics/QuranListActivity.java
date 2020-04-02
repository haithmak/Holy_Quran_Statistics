package com.example.holyquranstatistics;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class QuranListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new QuranListFragment();
    }


}
