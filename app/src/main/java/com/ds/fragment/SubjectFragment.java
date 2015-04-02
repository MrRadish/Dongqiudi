package com.ds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ds.adapter.SubjectAdapter;
import com.ds.dongqiudi.ArticleViewActivity;
import com.ds.dongqiudi.R;
import com.ds.entity.ArticleHead;
import com.ds.entity.SubjectArticle;
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
 * Created by Administrator on 2015/3/31.
 */
public class SubjectFragment extends Fragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView refresh;
    private String jsonUrl;
    private HttpUtils httpUtils;
    private List<SubjectArticle> data;
    private SubjectAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonUrl = getArguments().getString("url");
        httpUtils=new HttpUtils();
        data=new ArrayList<>();
        adapter=new SubjectAdapter(getActivity(),data);
        if(jsonUrl!=null){
            getData(jsonUrl);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_subject, container, false);
        refresh= ((PullToRefreshListView) view.findViewById(R.id.subject_refresh));
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setAdapter(adapter);
        refresh.setOnItemClickListener(this);
        return view;
    }
    public void getData(String jsonUrl){
        httpUtils.send(HttpRequest.HttpMethod.GET,jsonUrl,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> info) {
                data.addAll(JsonUtil.getData(info.result));
                adapter.addAll(data);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(),"网络加载失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(), ArticleViewActivity.class);
        if(data!=null&&data.size()>0){
            Bundle bundle=new Bundle();
            bundle.putString("articleurl",data.get(i).getApi());
            intent.putExtra("url",bundle);
            startActivity(intent);
        }
    }
}
