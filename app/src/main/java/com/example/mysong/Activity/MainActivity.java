package com.example.mysong.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mysong.Adapter.MainViewpagerAdapter;
import com.example.mysong.R;
import com.google.android.material.tabs.TabLayout;



public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new MainViewpagerAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.mainTab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.search);
    }
}
