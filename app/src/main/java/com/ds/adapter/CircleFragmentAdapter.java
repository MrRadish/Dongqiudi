package com.ds.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 圈子的适配器
 */
public class CircleFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment>list;

    public CircleFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);





        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();

    }
}
