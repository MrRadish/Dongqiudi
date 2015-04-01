package com.ds.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * listview 头部viewpageradapter
 * Created by Administrator on 2015/3/26.
 */
public class MainlistHaedadapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MainlistHaedadapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
