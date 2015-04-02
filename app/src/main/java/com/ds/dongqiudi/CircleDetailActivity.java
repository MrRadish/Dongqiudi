package com.ds.dongqiudi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.HotTopicAdapter;
import com.ds.entity.Groups;
import com.ds.entity.HotTopic;
import com.ds.utils.CircleURL;
import com.ds.utils.JsonToList;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class CircleDetailActivity extends ActionBarActivity implements PullToRefreshBase.OnRefreshListener2,
        AdapterView.OnItemClickListener{

    private PullToRefreshListView listView;
    private RequestQueue mQueue;
    private List<HotTopic> totalList;
    private int curPage=1;
    private ImageView imageView_empty;
    private HotTopicAdapter adapter;
    private List<Groups>totalList2;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rightcircle);
        id=getIntent().getIntExtra("id",0);
        mQueue= Volley.newRequestQueue(this);
        totalList=new ArrayList<>();
        totalList2=new ArrayList<>();
        adapter =new HotTopicAdapter(totalList,this,mQueue);

        imageView_empty = ((ImageView)findViewById(R.id.empty_bg));
        listView = ((PullToRefreshListView)findViewById(R.id.pulltorefresh_listview));
        //给ListView设置适配器
        listView.setAdapter(adapter);

        //listview加载数据为空的时候显示的图片
        listView.setEmptyView(imageView_empty);
        //设置该listview既可以下拉刷新，也可以上拉加载
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);

        //设置ListView的单击监听
        listView.setOnItemClickListener(this);

        //网络访问
        loadData();
    }

    public void loadData(){
        StringRequest jsonRequest=new StringRequest(CircleURL.getCircleURL+id+"?page="+curPage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        List<HotTopic> list = JsonToList.hotTopicJsonToList(s);
                        if (list != null) {
                            updateAdapter(list);
                        }else{
                            Toast.makeText(CircleDetailActivity.this, "数据加载失败！", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(CircleDetailActivity.this,"网络访问失败!!!!!",Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(jsonRequest);
    }

    private void updateAdapter(List<HotTopic> list) {
        totalList.addAll(list);
        //通知适配器更新
        adapter.notifyDataSetChanged();

        //数据加载完毕后取消刷洗
        listView.onRefreshComplete();
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        curPage=1;
        totalList.clear();
        loadData();
        
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        curPage++;
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HotTopic childAt = (HotTopic) adapterView.getItemAtPosition(i);
        Intent intent=new Intent(this, TopicDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("id",childAt.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
