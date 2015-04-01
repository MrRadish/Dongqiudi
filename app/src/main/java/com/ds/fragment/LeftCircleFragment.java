package com.ds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.LeftCircleAdapter;
import com.ds.dongqiudi.R;
import com.ds.entity.Groups;
import com.ds.entity.League;
import com.ds.entity.LeftCircleItem;
import com.ds.utils.CircleURL;
import com.ds.utils.JsonToList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-24.
 */
public class LeftCircleFragment extends Fragment {


    private RecyclerView listview;
    private RequestQueue mQueue;
    private StringRequest stringRequest;
    private List<LeftCircleItem>totalList;
    private LeftCircleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue= Volley.newRequestQueue(getActivity());
        totalList=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leftcircle,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listview = ((RecyclerView) view.findViewById(R.id.listview_leftcircle));
        loadData();
        adapter=new LeftCircleAdapter(totalList,getActivity(),mQueue);
       listview.setLayoutManager(new LinearLayoutManager(getActivity()));
       listview.setAdapter(adapter);

    }

    //配置数据源
    public void loadData(){
        stringRequest= new StringRequest(CircleURL.circleURL,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            List<Groups>list= JsonToList.circleJsonToList(s);
            List<LeftCircleItem>list1=new ArrayList<>();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Groups groups = list.get(i);
                    if(groups.getList()!=null){
                        for (int j = 0; j < groups.getList().size();) {
                            LeftCircleItem item=new LeftCircleItem();
                            item.setGroupName(groups.getName());
                            League league1=new League();
                            league1.setLeague_id(groups.getList().get(j).getLeague_id());
                            league1.setThumb(groups.getList().get(j).getThumb());
                            league1.setId(groups.getList().get(j).getId());
                            league1.setJoin_user_total(groups.getList().get(j).getJoin_user_total());
                            league1.setTitle(groups.getList().get(j).getTitle());
                            league1.setTopic_total(groups.getList().get(j).getTopic_total());
                            item.setLeague1(league1);

                            League league2=new League();
                            if (j+1<groups.getList().size()){
                                league2.setLeague_id(groups.getList().get(j+1).getLeague_id());
                                league2.setThumb(groups.getList().get(j+1).getThumb());
                                league2.setId(groups.getList().get(j+1).getId());
                                league2.setJoin_user_total(groups.getList().get(j+1).getJoin_user_total());
                                league2.setTitle(groups.getList().get(j+1).getTitle());
                                league2.setTopic_total(groups.getList().get(j+1).getTopic_total());
                                item.setLeague2(league2);
                                list1.add(item);
                            }else {
                                league2=null;
                                item.setLeague2(league2);
                                list1.add(item);
                                break;
                            }

                            j+=2;
                            if (j>=groups.getList().size()){
                                break;
                            }

                        }
                    }
                }
            }
                totalList.addAll(list1);
                adapter.notifyDataSetChanged();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"网络访问失败！！",Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(stringRequest);
    }


}
