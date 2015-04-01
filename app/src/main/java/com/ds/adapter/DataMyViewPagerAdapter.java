package com.ds.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ds.dongqiudi.R;
import com.ds.fragment.DataViewpagerExtrafragment;
import com.ds.fragment.DataViewpagerfragment;

import java.util.List;

/**
 * viewpager的适配器
 * Created by aaa on 15-3-31.
 */
public class DataMyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public DataMyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList)
    {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment item=null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                 item = (DataViewpagerfragment) fragmentList.get(0);
                bundle.clear();
                bundle.putInt("id", 0);
                item.setArguments(bundle);
                break;
            case 1:
                item= (DataViewpagerfragment) fragmentList.get(1);
                bundle.clear();
                bundle.putInt("id", 1);
                item.setArguments(bundle);
                break;
            case 2:
               item= (DataViewpagerfragment) fragmentList.get(2);
              bundle.clear();
               bundle.putInt("id", 2);
                item.setArguments(bundle);
                break;
            case 3:
                item= (DataViewpagerfragment) fragmentList.get(3);
                bundle.clear();
                bundle.putInt("id", 3);
                item.setArguments(bundle);
                break;
            case 4:
                item= (DataViewpagerfragment) fragmentList.get(4);
                bundle.clear();
                bundle.putInt("id", 4);
                item.setArguments(bundle);

                break;
            case 5:
                item= (DataViewpagerfragment) fragmentList.get(5);
                bundle.clear();
                bundle.putInt("id", 5);
                item.setArguments(bundle);
                break;
            case 6:
                item= (DataViewpagerfragment) fragmentList.get(6);
                bundle.clear();
                bundle.putInt("id", 6);
                item.setArguments(bundle);
                break;
            case 7:
                item= (DataViewpagerExtrafragment) fragmentList.get(7);
                bundle.clear();
                bundle.putInt("id", 7);
                item.setArguments(bundle);
                break;
            case 8:

                item= (DataViewpagerfragment) fragmentList.get(8);
                bundle.clear();
                bundle.putInt("id", 8);
                item.setArguments(bundle);
                break;
            case 9:
                item= (DataViewpagerfragment) fragmentList.get(9);
                bundle.clear();
                bundle.putInt("id", 9);
                item.setArguments(bundle);
                break;
            case 10:
                item= (DataViewpagerfragment) fragmentList.get(10);
                bundle.clear();
                bundle.putInt("id", 10);
                item.setArguments(bundle);
                break;
            case 11:
                item= (DataViewpagerfragment) fragmentList.get(11);
                bundle.clear();
                bundle.putInt("id", 11);
                item.setArguments(bundle);
                break;
            case 12:
                item= (DataViewpagerExtrafragment) fragmentList.get(12);
                bundle.clear();
                bundle.putInt("id", 12);
                item.setArguments(bundle);
                break;
            case 13:
                item= (DataViewpagerfragment) fragmentList.get(13);
                bundle.clear();
                bundle.putInt("id", 13);
                item.setArguments(bundle);
                break;
            case 14:
                item= (DataViewpagerExtrafragment) fragmentList.get(14);
                bundle.clear();
                bundle.putInt("id", 14);
                item.setArguments(bundle);
                break;
            case 15:
                item= (DataViewpagerfragment) fragmentList.get(15);
                bundle.clear();
                bundle.putInt("id", 15);
                item.setArguments(bundle);
                break;
            case 16:
                item= (DataViewpagerfragment) fragmentList.get(16);
                bundle.clear();
                bundle.putInt("id", 16);
                item.setArguments(bundle);
                break;
            case 17:
                item= (DataViewpagerfragment) fragmentList.get(17);
                bundle.clear();
                bundle.putInt("id", 17);
                item.setArguments(bundle);
                break;
        }
        return item;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
