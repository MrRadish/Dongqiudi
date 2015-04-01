package com.ds.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.ds.adapter.MainRefreshAdapter;
import com.ds.dongqiudi.R;
import com.ds.entity.MainArticle;
import com.ds.utils.JsonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页,第三到最后的fragment
 * Created by Administrator on 2015/3/25.
 */
public class DumFragmentB extends Fragment implements PullToRefreshBase.OnRefreshListener2{
    private PullToRefreshListView refresh;
    private String url;
    private List<MainArticle> data;
    private MainRefreshAdapter adapter;
    private Handler handler=new Handler();
    private HttpUtils httpUtils;
    private TextView txtnull;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
        httpUtils=new HttpUtils();
        Log.d("FragmentB",url);
        data=new ArrayList<>();
        adapter = new MainRefreshAdapter(getActivity(), data);
        if(url!=null){
            //下载json数据
            getData(url);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dum_a, container, false);
        txtnull = ((TextView) view.findViewById(R.id.txt_null));
        txtnull.setVisibility(View.GONE);
        refresh= ((PullToRefreshListView) view.findViewById(R.id.refresh_dum_a));
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(this);
        refresh.setAdapter(adapter);

        return view;
    }
    //下载json数据
    public void getData(String jsonUrl){
        Log.d("下载json数据","下载数据的方法");
        httpUtils.send(HttpRequest.HttpMethod.GET,jsonUrl,new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                Toast.makeText(getActivity(), "正在加载数据", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(ResponseInfo<String> info) {
                Log.d("下载json数据",info.result);
                data.addAll(JsonUtil.getMainJson(info.result));
                adapter.addAll(data);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "加载失败，网络问题", Toast.LENGTH_SHORT).show();
                refresh.setVisibility(View.GONE);
                txtnull.setVisibility(View.VISIBLE);
            }
        });
    }
    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(url!=null){
                    //
                    getData(url);
                    adapter.addAll(data);
                    refresh.onRefreshComplete();
                }
            }
        },2000);

    }

    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(data!=null&&data.size()>0){
                    getData(data.get(0).getNextUrl().toString());
                    adapter.addAll(data);
                }
            }
        },2000);

    }
}
