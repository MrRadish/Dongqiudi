package com.ds.fragment;





import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.ds.adapter.Game_ViewPagerAdapter;
import com.ds.dongqiudi.R;

import java.util.ArrayList;
import java.util.List;


public class GameFragment extends Fragment {
    private RadioGroup game_titleGroup;
    private ViewPager game_MyViewpager;
    private FragmentPagerAdapter adapter;
    private List<Fragment> adapterList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game,container,false);
        initFragmet(view);
        return view;
    }

    public void initFragmet(View view) {
        //得到保存Radio的Group
        game_titleGroup = ((RadioGroup) view.findViewById(R.id.game_titleGroup));
        ((RadioButton) game_titleGroup.getChildAt(0)).setChecked(true);
        ((RadioButton) game_titleGroup.getChildAt(0)).setTextColor(getResources().getColor(R.color.titles_text));
        //实例化一个ViewPager,给list中添加Fragment数据
        adapterList.add(new GameMainFragment());
        adapterList.add(new GameMainFragment());
        adapterList.add(new GameAttentionFragment());
        //实例化ViewPager
        game_MyViewpager = ((ViewPager) view.findViewById(R.id.game_MyViewpager));
        FragmentManager fm = getChildFragmentManager();
        adapter = new Game_ViewPagerAdapter(fm, adapterList);
        game_MyViewpager.setAdapter(adapter);

        //设置联动，让按键控制ViewPager
        game_titleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.game_titles1:
                        for (int a = 0; a < radioGroup.getChildCount(); a++) {
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        game_MyViewpager.setCurrentItem(0);
                        ((RadioButton) radioGroup.getChildAt(0)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;

                    case R.id.game_titles2:
                        for (int a = 0; a < radioGroup.getChildCount(); a++) {
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        game_MyViewpager.setCurrentItem(1);
                        ((RadioButton) radioGroup.getChildAt(1)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;

                    case R.id.game_titles3:
                        for (int a = 0; a < radioGroup.getChildCount(); a++) {
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        game_MyViewpager.setCurrentItem(2);
                        ((RadioButton) radioGroup.getChildAt(2)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;
                }
            }


        });

        game_MyViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) game_titleGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
