package com.ds.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.ds.adapter.MainRefreshAdapter;
import com.ds.adapter.MainlistHaedadapter;
import com.ds.dongqiudi.ArticleViewActivity;
import com.ds.dongqiudi.R;
import com.ds.entity.ArticleHead;
import com.ds.entity.MainArticle;
import com.ds.utils.DBHelper;
import com.ds.utils.JsonUtil;
import com.ds.view.MyViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
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
 * 首页viewpager里头条和视频使用的fragment
 * Created by Administrator on 2015/3/24.
 */
public class DumFragmentA extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView>, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private PullToRefreshListView refresh;
    private MainRefreshAdapter adapter;
    private List<MainArticle> data;
    private HttpUtils httpUtils;
    private List<Fragment> fragments;
    private Handler handler=new Handler();
    private ViewPager headViewpager;
    private RadioGroup group;
    private boolean mark=true;
    private int index;
    private MyRunnable myRunnable=new MyRunnable();
    private View v;
    private ListView listView;
    private List<ArticleHead> heads;
    private MainlistHaedadapter headadapter;
    private String url;
    private TextView txtnull;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUtils=new HttpUtils();
        data=new ArrayList<>();
        heads=new ArrayList<ArticleHead>();
        fragments=new ArrayList<Fragment>();
        adapter=new MainRefreshAdapter(getActivity(),data);
        //
        url = getArguments().getString("url");
        if(url !=null){
            getData(url);
        }
//        try {
//            data=DBHelper.getUtils().findAll(MainArticle.class);
//            adapter.addAll(data);
//
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dum_a, container, false);
        txtnull = ((TextView) view.findViewById(R.id.txt_null));
        txtnull.setVisibility(View.GONE);
        refresh= ((PullToRefreshListView) view.findViewById(R.id.refresh_dum_a));
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(this);
        //设置头部
        listView=refresh.getRefreshableView();
        //加载自定义控件
        v= inflater.inflate(R.layout.main_item_head, null);
        listView.addHeaderView(v);
        //获取头部viewpager
        headViewpager = ((MyViewPager) v.findViewById(R.id.main_list_head_viewpager));
        group = ((RadioGroup) v.findViewById(R.id.main_list_head_item_rdgroup));

        //第一个默认选中
        View rdBtn=group.getChildAt(0);
        if(rdBtn!=null&&rdBtn instanceof RadioButton){
            RadioButton rd= ((RadioButton) rdBtn);
            rd.setChecked(true);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }
    //下载json数据(下拉刷新)
    public void getData(String jsonUrl){

        httpUtils.send(HttpRequest.HttpMethod.GET, jsonUrl, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
            }
            @Override
            public void onSuccess(ResponseInfo<String> info) {
                try {
                    ArticleHead head = new ArticleHead();
                    List<MainArticle> list=new ArrayList<MainArticle>();
                    JSONObject jsonObject = new JSONObject(info.result);
                    String prev = jsonObject.getString("prev");
                    if (prev != null) {
                        head.setHeadUrl(prev);
                        heads.add(head);
                        //下载头部json数据
                        getHead(heads.get(0).getHeadUrl());
                    }
                    JSONArray objs = jsonObject.getJSONArray("articles");
                    for (int i = 0; i < objs.length(); i++) {
                        MainArticle article = new MainArticle();
                        article.setNextUrl(jsonObject.getString("next"));
                        JSONObject obj = (JSONObject) objs.get(i);
                        article.setId(obj.getInt("id"));
                        article.setTitle(obj.getString("title"));
                        article.setDescription(obj.optString("description"));
                        article.setComments(obj.getInt("comments_total"));
                        article.setShareUrl(obj.getString("share"));
                        article.setImgUrl(obj.getString("thumb"));
                        article.setPublishTime(obj.getString("published_at"));
                        article.setArticleUrl(obj.getString("url"));
                        article.setLabel(obj.optString("label"));
                        article.setLabelColor(obj.optString("label_color"));
//                        JSONObject sbj=obj.optJSONObject("album");
//                        article.setSbjImgCount(sbj.optInt("total"));
//                        JSONArray sbjarray=sbj.getJSONArray("pics");
//                        List<String> list1=new ArrayList<String>();
//                        for(i=0;i<sbjarray.length();i++){
//                            list1.add(sbjarray.get(i).toString());
//                           article.setSbjImgUrl(list1);
//                        }
                        list.add(article);
                    }
                    data.addAll(list);
                    adapter.addAll(data);
//                    try {
//                        //缓存数据
//                        DBHelper.getUtils().saveOrUpdateAll(list);
//                    } catch (DbException e) {
//                        e.printStackTrace();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "加载失败，网络问题", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "加载失败，网络问题", Toast.LENGTH_SHORT).show();
                refresh.setVisibility(View.GONE);
                txtnull.setVisibility(View.VISIBLE);
            }
        });


    }
    //https://api.dongqiudi.com/tabs/2/android/1.json?before=1427687417
    /**
     * 下载头部json数据
     */
    private void getHead(String headUrl) {
        httpUtils.send(HttpRequest.HttpMethod.GET, headUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> info) {
                if (info != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(info.result);
                        JSONArray array = jsonObject.getJSONArray("recommend");
                        for (int i = 0; i < array.length(); i++) {
                            ArticleHead head = new ArticleHead();
                            JSONObject obj = (JSONObject) array.get(i);
                            head.setId(obj.getInt("id"));
                            head.setTitle(obj.getString("title"));
                            head.setImgUrl(obj.getString("thumb"));
                            head.setComments(obj.getInt("comments_total"));
                            head.setPublishTime(obj.getString("published_at"));
                            head.setLabel(obj.optString("label"));
                            head.setLabelColor(obj.optString("label_color"));
                            head.setArticle(obj.getString("url"));
                            MainListHeadFragment fragment = new MainListHeadFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("entity", head);
                            fragment.setArguments(bundle);
                            fragments.add(fragment);
//                            Log.d("fragments-------------", head.toString());
//                            try {
//                                //缓存头部
//                                DBHelper.getUtils().saveOrUpdate(head);
//                            } catch (DbException e) {
//                                e.printStackTrace();
//                            }
                        }

                        headadapter = new MainlistHaedadapter(getChildFragmentManager(), fragments);
                        if(headViewpager!=null){
                            headViewpager.setAdapter(headadapter);
                            headViewpager.setOnPageChangeListener(DumFragmentA.this);
                            group.setOnCheckedChangeListener(DumFragmentA.this);
                            handler.postDelayed(myRunnable,2000);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "加载失败，网络问题", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ///////头部viewpager和radiogroup联动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) group.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case 0:
                headViewpager.setCurrentItem(0);
                break;
            case 1:
                headViewpager.setCurrentItem(1);
                break;
            case 2:
                headViewpager.setCurrentItem(2);
                break;
            case 3:
                headViewpager.setCurrentItem(3);
                break;
            case 4:
                headViewpager.setCurrentItem(4);
                break;
        }
    }

    /**
     * listitem点击事件
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(), ArticleViewActivity.class);
        if(data!=null&&data.size()>0){
            Bundle bundle=new Bundle();
            bundle.putString("articleurl",data.get(i-2).getArticleUrl());
            intent.putExtra("url",bundle);
            startActivity(intent);
        }

    }

    //头部转动
    class MyRunnable implements Runnable {

        @Override
        public void run() {
            if (mark) {
                index++;
                index = index % 4;
                headViewpager.setCurrentItem(index, true);
                handler.postDelayed(myRunnable, 3000);
            }
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        //延时发送消息
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.clear();
                getData(url);
                adapter.addAll(data);
                refresh.onRefreshComplete();
            }
        },2000);
    }

    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(data.size()>0&&data!=null){
                    getData(data.get(0).getNextUrl());
//                    Log.d("next--------------",data.toString());
                    adapter.addAll(data);
                    refresh.onRefreshComplete();
                }
            }
        },2000);
    }
}

