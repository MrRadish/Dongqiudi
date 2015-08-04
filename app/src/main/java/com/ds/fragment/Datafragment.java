package com.ds.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ds.adapter.DataMyViewPagerAdapter;
import com.ds.dongqiudi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-25.
 */
public class Datafragment extends Fragment implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private ViewPager viewPager_data;
    private RadioGroup radioGroup_data;
    private DataMyViewPagerAdapter pagerAdapter;
    private Fragment viewpagerfragment;
    //
    private RadioButton rb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        init(view);
        return view;
    }

    public void init(View view) {
        viewPager_data = (ViewPager) view.findViewById(R.id.viewpager_data_detail);
        radioGroup_data = (RadioGroup) view.findViewById(R.id.data_rg_top);
        rb = (RadioButton) radioGroup_data.getChildAt(0);
        rb.setChecked(true);
        radioGroup_data.setOnCheckedChangeListener(this);
        for (int i = 0; i < 18; i++) {
             viewpagerfragment = new DataViewpagerfragment();
            if(i==7 || i==12 || i==14)
            {
                viewpagerfragment=new DataViewpagerExtrafragment();
            }
            fragmentList.add(viewpagerfragment);
        }
        pagerAdapter = new DataMyViewPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        viewPager_data.setAdapter(pagerAdapter);

        viewPager_data.setOnPageChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 单选按钮组的监听事件
     *
     * @param radioGroup
     * @param i
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.data_rb_1:
                viewPager_data.setCurrentItem(0);

                break;
            case R.id.data_rb_2:

                viewPager_data.setCurrentItem(1);

                break;
            case R.id.data_rb_3:
                viewPager_data.setCurrentItem(2);
                break;
            case R.id.data_rb_4:

                viewPager_data.setCurrentItem(3);
                break;
            case R.id.data_rb_5:
                viewPager_data.setCurrentItem(4);
                break;
            case R.id.data_rb_6:
                viewPager_data.setCurrentItem(5);
                break;
            case R.id.data_rb_7:
                viewPager_data.setCurrentItem(6);
                break;
            case R.id.data_rb_8:
                viewPager_data.setCurrentItem(7);
                break;
            case R.id.data_rb_9:
                viewPager_data.setCurrentItem(8);
                break;
            case R.id.data_rb_10:
                viewPager_data.setCurrentItem(9);
                break;
            case R.id.data_rb_11:
                viewPager_data.setCurrentItem(10);
                break;
            case R.id.data_rb_12:
                viewPager_data.setCurrentItem(11);
                break;
            case R.id.data_rb_13:
                viewPager_data.setCurrentItem(12);
                break;
            case R.id.data_rb_14:
                viewPager_data.setCurrentItem(13);
                break;
            case R.id.data_rb_15:
                viewPager_data.setCurrentItem(14);
                break;
            case R.id.data_rb_16:
                viewPager_data.setCurrentItem(15);
                break;
            case R.id.data_rb_17:
                viewPager_data.setCurrentItem(16);
                break;
            case R.id.data_rb_18:
                viewPager_data.setCurrentItem(17);
                break;


        }

    }

    //下面三个是viewpager监听实现的方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        rb = (RadioButton) radioGroup_data.getChildAt(position);
        rb.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}