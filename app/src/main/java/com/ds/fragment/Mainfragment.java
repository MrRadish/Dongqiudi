package com.ds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ds.adapter.MainFragmentViewPagerAdapter;
import com.ds.dongqiudi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页按钮显示的fragment
 * Created by Administrator on 2015/3/24.
 */
public class Mainfragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    List<Fragment> fragments;
    private RadioGroup group;
    private HorizontalScrollView scrollView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        init(v);
        return v;
    }
    private void init(View view){
        group= ((RadioGroup) view.findViewById(R.id.top_rd_group));
        //默认头条选中
        ((RadioButton) group.getChildAt(0)).setChecked(true);
        scrollView= ((HorizontalScrollView) view.findViewById(R.id.hscrollview));
        int length=group.getChildCount();
        //设置导航条显示的内容
        String url=null;
        for(int i=0;i<length;i++){
            if(i<2){
              fragments.add(new DumFragmentA());
            }
            else if(i==4){
                fragments.add(new SubjectFragment());
            }
            else {
              fragments.add(new DumFragmentB());
            }
        }

        viewPager= ((ViewPager) view.findViewById(R.id.viewPager_main));
        viewPager.setAdapter(new MainFragmentViewPagerAdapter(
                    getChildFragmentManager(),fragments));
        //导航条,viewpager联动
        group.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rd_headline:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rd_video:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.rd_collection:
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.rd_depth:
                viewPager.setCurrentItem(3,false);
                break;
            case R.id.rd_subject:
                viewPager.setCurrentItem(4,false);
                break;
            case R.id.rd_topic:
                viewPager.setCurrentItem(5,false);
                break;
            case R.id.rd_footballer:
                viewPager.setCurrentItem(6,false);
                break;
            case R.id.rd_xianqing:
                viewPager.setCurrentItem(7,false);
                break;
            case R.id.rd_zhongchao:
                viewPager.setCurrentItem(8,false);
                break;
            case R.id.rd_zhongjia:
                viewPager.setCurrentItem(9,false);
                break;
            case R.id.rd_epl:
                viewPager.setCurrentItem(10,false);
                break;
            case R.id.rd_yijia:
                viewPager.setCurrentItem(11,false);
                break;
            case R.id.rd_xijia:
                viewPager.setCurrentItem(12,false);
                break;
            case R.id.rd_dejia:
                viewPager.setCurrentItem(13,false);
                break;
            case R.id.rd_wuzhou:
                viewPager.setCurrentItem(14,false);
                break;
            case R.id.rd_zhuanhui:
                viewPager.setCurrentItem(15,false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        RadioButton childAt = (RadioButton) group.getChildAt(position);
        int left = childAt.getLeft();
        scrollView.smoothScrollTo(left-1,0);
        childAt.setChecked(true);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
