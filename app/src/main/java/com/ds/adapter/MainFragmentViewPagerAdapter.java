package com.ds.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ds.fragment.CollectionFragment;
import com.ds.fragment.DumFragmentA;
import com.ds.fragment.DumFragmentB;
import com.ds.fragment.TopicFragment;
import com.ds.utils.MainHttpUrls;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class MainFragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public MainFragmentViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        String url=null;
        switch (position){
            case 0:
                DumFragmentA dgA1= (DumFragmentA) list.get(0);
                url= MainHttpUrls.MAIN_URL;
                Bundle bundle=new Bundle();
                bundle.putString("url",url);
                dgA1.setArguments(bundle);

                break;
            case 1:
                DumFragmentA dgA2 = (DumFragmentA) list.get(1);
                url= MainHttpUrls.VIDEO_URL;
                Bundle bundle1=new Bundle();
                bundle1.putString("url",url);
                dgA2.setArguments(bundle1);
                break;
            case 2:
                DumFragmentB dgB1 = (DumFragmentB) list.get(2);
                url= MainHttpUrls.COLLECTION_URL;
                Bundle bundle2=new Bundle();
                bundle2.putString("url",url);
                dgB1.setArguments(bundle2);

                break;
            case 3:
                DumFragmentB dgB2 = (DumFragmentB) list.get(3);
                url= MainHttpUrls.DEPTH_URL;
                Bundle bundle3=new Bundle();
                bundle3.putString("url",url);
                dgB2.setArguments(bundle3);
                break;
            case 4:
                TopicFragment dgB3 = (TopicFragment) list.get(4);
                url= MainHttpUrls.SUBJECT_URL;
                Bundle bundle4=new Bundle();
                bundle4.putString("url",url);
                dgB3.setArguments(bundle4);
                break;
            case 5:
                DumFragmentB dgB4 = (DumFragmentB) list.get(5);
                url= MainHttpUrls.TOPIC_URL;
                Bundle bundle5=new Bundle();
                bundle5.putString("url",url);
                dgB4.setArguments(bundle5);
                break;
            case 6:
                DumFragmentB dgB5 = (DumFragmentB) list.get(6);
                url= MainHttpUrls.FOOTBALLER_URL;
                Bundle bundle6=new Bundle();
                bundle6.putString("url",url);
                dgB5.setArguments(bundle6);
                break;
            case 7:
                DumFragmentB dgB6 = (DumFragmentB) list.get(7);
                url= MainHttpUrls.XIANQING_URL;
                Bundle bundle7=new Bundle();
                bundle7.putString("url",url);
                dgB6.setArguments(bundle7);
                break;
            case 8:
                DumFragmentB dgB7 = (DumFragmentB) list.get(8);
                url= MainHttpUrls.ZHONGCHAO_URL;
                Bundle bundle8=new Bundle();
                bundle8.putString("url",url);
                dgB7.setArguments(bundle8);
                break;
            case 9:
                DumFragmentB dgB8 = (DumFragmentB) list.get(9);
                url= MainHttpUrls.ZHONGJIA_URL;
                Bundle bundle9=new Bundle();
                bundle9.putString("url",url);
                dgB8.setArguments(bundle9);
                break;
            case 10:
                DumFragmentB dgB9 = (DumFragmentB) list.get(10);
                url= MainHttpUrls.YIJIA_URL;
                Bundle bundle10=new Bundle();
                bundle10.putString("url",url);
                dgB9.setArguments(bundle10);
                break;
            case 11:
                DumFragmentB dgB10 = (DumFragmentB) list.get(11);
                url= MainHttpUrls.XIJIA_URL;
                Bundle bundle11=new Bundle();
                bundle11.putString("url",url);
                dgB10.setArguments(bundle11);
                break;
            case 12:
                DumFragmentB dgB11 = (DumFragmentB) list.get(12);
                url= MainHttpUrls.DEJIA_URL;
                Bundle bundle12=new Bundle();
                bundle12.putString("url",url);
                dgB11.setArguments(bundle12);
                break;
            case 13:
                DumFragmentB dgB12 = (DumFragmentB) list.get(13);
                url= MainHttpUrls.WUZHOU_URL;
                Bundle bundle13=new Bundle();
                bundle13.putString("url",url);
                dgB12.setArguments(bundle13);
                break;
            case 14:
                DumFragmentB dgB13 = (DumFragmentB) list.get(14);
                url= MainHttpUrls.ZHUANHUI_URL;
                Bundle bundle14=new Bundle();
                bundle14.putString("url",url);
                dgB13.setArguments(bundle14);
                break;
            case 15:
                DumFragmentB dgB14 = (DumFragmentB) list.get(15);
                url= MainHttpUrls.TOPIC_URL;
                Bundle bundle15=new Bundle();
                bundle15.putString("url",url);
                dgB14.setArguments(bundle15);
                break;

        }
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
