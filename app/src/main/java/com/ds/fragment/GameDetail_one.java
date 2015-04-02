package com.ds.fragment;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.Game_Field_ViewPagerAdapter;
import com.ds.dongqiudi.R;
import com.ds.utils.MyBitmapCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-28.
 */
public class GameDetail_one extends ActionBarActivity implements
        RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    private RequestQueue mRequestQueue;
    private ImageLoader.ImageCache mImageCahce = MyBitmapCache.getInstance();
    private ViewPager game_field_viewpager;
    private ImageLoader mImageLoader;
    private List<Fragment> fragementList = new ArrayList<>();
    private Game_Field_ViewPagerAdapter myViewPagerAdapter;
    private RadioGroup game_field_group;
    private String team_A_id;
    private String team_B_id;
    private String fs_A;
    private String fs_B;
    private String date_utc;
    private String competition_name;
    private String match_id;
    private ImageView gamedetail_one_left;
    private ImageView gamedetail_one_right;
    private TextView gamedetail_one_score;
    private TextView gamedetail_one_competition_name;
    private TextView gamedetail_one_date_utc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(mRequestQueue,mImageCahce);
        setContentView(R.layout.activity_gamedetail_one);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        team_A_id = bundle.getString("team_A_id");
        team_B_id = bundle.getString("team_B_id");
        fs_A = bundle.getString("fs_A");
        fs_B = bundle.getString("fs_B");
        date_utc = bundle.getString("date_utc");
        competition_name = bundle.getString("competition_name");
        match_id = bundle.getString("match_id");
        init();
    }

    public void init() {
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putString("A",team_A_id);
        fragmentBundle.putString("B",team_B_id);
        fragmentBundle.putString("id",match_id);
        //实例化RadioGroup并给予监听
        game_field_group = ((RadioGroup) findViewById(R.id.game_field_group));
        game_field_group.setOnCheckedChangeListener(this);
        //给ViewPager实例并添加适配器
        game_field_viewpager = ((ViewPager) findViewById(R.id.game_field_viewpager));
        game_field_viewpager.setOnPageChangeListener(this);
        //传递id给每一个fragment
        Fragment chatFragment = new chatGameDetailFragment();
        chatFragment.setArguments(fragmentBundle);
        fragementList.add(chatFragment);
        Fragment incidentFragment = new incidentGameDetailFragment();
        incidentFragment.setArguments(fragmentBundle);
        fragementList.add(incidentFragment);
        fragementList.add( new GameField());
        Fragment newsFragment = new newsGameDetailFragment();
        newsFragment.setArguments(fragmentBundle);
        fragementList.add(newsFragment);
        fragementList.add( new GameField());
        FragmentManager fm = getSupportFragmentManager();
        myViewPagerAdapter = new Game_Field_ViewPagerAdapter(fm, fragementList);
        game_field_viewpager.setAdapter(myViewPagerAdapter);
        ((RadioButton) game_field_group.getChildAt(0)).setChecked(true);
        //实例化每一个需要添加数据的控件
        gamedetail_one_left = ((ImageView) findViewById(R.id.gamedetail_one_left));
        gamedetail_one_right = ((ImageView) findViewById(R.id.gamedetail_one_right));
        gamedetail_one_score = ((TextView) findViewById(R.id.gamedetail_one_score));
        gamedetail_one_competition_name = ((TextView) findViewById(R.id.gamedetail_one_competition_name));
        gamedetail_one_date_utc = ((TextView) findViewById(R.id.gamedetail_one_date_utc));
        //给所有的控件添加数据
        for (int i = 0;i<4;i++){
            String [] a = getResources().getStringArray(R.array.game_detail_title);
            ((RadioButton) game_field_group.getChildAt(i)).setText(a[i]);
        }
        gamedetail_one_date_utc.setText(date_utc);
        gamedetail_one_competition_name.setText(competition_name);
        gamedetail_one_score.setText(fs_A+" "+"-"+" "+fs_B);
        mImageLoader.get("http://img.dongqiudi.com/data/pic/"+team_A_id+".png",
                ImageLoader.getImageListener(gamedetail_one_left,R.drawable.blank,
                        R.drawable.hanguo),0,0);
        mImageLoader.get("http://img.dongqiudi.com/data/pic/"+team_B_id+".png",
                ImageLoader.getImageListener(gamedetail_one_right,R.drawable.blank,
                        R.drawable.hanguo),0,0);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.game_field1:
                        game_field_viewpager.setCurrentItem(0);
                        for (int a = 0;a<4;a++){
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        ((RadioButton) radioGroup.getChildAt(0)).setTextColor(getResources().getColor(R.color.titles_text));
                    break;

                    case R.id.game_field2:
                        game_field_viewpager.setCurrentItem(1);
                        for (int a = 0;a<4;a++){
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        ((RadioButton) radioGroup.getChildAt(1)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;

                    case R.id.game_field3:
                        game_field_viewpager.setCurrentItem(2);
                        for (int a = 0;a<4;a++){
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        ((RadioButton) radioGroup.getChildAt(2)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;

                    case R.id.game_field4:
                        game_field_viewpager.setCurrentItem(3);
                        for (int a = 0;a<4;a++){
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        ((RadioButton) radioGroup.getChildAt(3)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;

                    case R.id.game_field5:
                        game_field_viewpager.setCurrentItem(4);
                        for (int a = 0;a<4;a++){
                            ((RadioButton) radioGroup.getChildAt(a)).setTextColor(getResources().getColor(R.color.titles_bleak));
                        }
                        ((RadioButton) radioGroup.getChildAt(4)).setTextColor(getResources().getColor(R.color.titles_text));
                        break;
                }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) game_field_group.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
