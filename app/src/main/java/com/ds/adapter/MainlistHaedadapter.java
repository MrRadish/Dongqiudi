package com.ds.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.ds.entity.ArticleHead;
import com.ds.fragment.MainListHeadFragment;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * listview 头部viewpageradapter
 * Created by Administrator on 2015/3/26.
 */
public class MainlistHaedadapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<ArticleHead> data;
    public MainlistHaedadapter(FragmentManager fm,List<Fragment> fragments,List<ArticleHead> data) {
        super(fm);
        this.fragments=fragments;
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        MainListHeadFragment fragment= (MainListHeadFragment) fragments.get(position);
        Bundle bundle=new Bundle();
        bundle.putSerializable("entity",data.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
