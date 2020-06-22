package com.example.mysong.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mysong.Fragment.FragmentHomePage;
import com.example.mysong.Fragment.FragmentSearch;


public class MainViewpagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentArray = new Fragment[2];
    private String[] titleArray = new String[2];
    public MainViewpagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentArray = new Fragment[] {
                new FragmentHomePage(),
                new FragmentSearch()
        };
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArray[position];
    }

    @Override
    public int getCount() {
        return fragmentArray.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        titleArray = new String[] {"Trang chu", "Tim kiem"};
        return titleArray[position];
    }
}
