package com.ds.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.ds.adapter.CircleFragmentAdapter;
import com.ds.dongqiudi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-24.
 */
public class CircleFragmennt extends Fragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private ViewPager viewPager;
    private RadioGroup radioGroup_title;
    //数据源
    private List<Fragment>totalList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circle,container,false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {

        //初始化控件和数据源
        init(view);

        //给viewpager设置适配器
        viewPager.setAdapter(new CircleFragmentAdapter(
                getChildFragmentManager(),totalList));

        //给viewpager设置监听器，当页面改变时，让相应的标题也改变
        viewPager.setOnPageChangeListener(this);

        //给radiogroup设置改变监听器
        radioGroup_title.setOnCheckedChangeListener(this);
    }

    private void init(View view) {
        radioGroup_title=(RadioGroup) view.findViewById(R.id.titlebar_circlefragment);
        //默认情况下设置第一个为被选中
        ((RadioButton) radioGroup_title.getChildAt(0)).setChecked(true);
        viewPager = ((ViewPager) view.findViewById(R.id.viewpager_circle));
        //填充数据源
        totalList.add(new LeftCircleFragment());
        totalList.add(new RightCircleFragment());
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

       ((RadioButton) radioGroup_title.getChildAt(position)).setChecked(true);

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup,int i) {
        switch(i){
            case R.id.circle_circlefragment:
                viewPager.setCurrentItem(0);
                break;
            case R.id.hot_topic_circlefragment:
                viewPager.setCurrentItem(1);
                break;

        }

    }
}
