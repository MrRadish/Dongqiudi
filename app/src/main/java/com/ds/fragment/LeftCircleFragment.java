package com.ds.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.LeftCircleAdapter;
import com.ds.dongqiudi.CircleDetailActivity;
import com.ds.dongqiudi.R;
import com.ds.entity.Groups;
import com.ds.entity.League;
import com.ds.utils.CircleURL;
import com.ds.utils.JsonToList;
import com.ds.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-24.
 */
public class LeftCircleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private RequestQueue mQueue;
    private StringRequest stringRequest;
    private List<Groups>totalList;
    private MyGridView gridview_Xingqu;
    private MyGridView gridview_Yingchao;
    private MyGridView gridview_Xijia;
    private MyGridView gridview_Dejia;
    private MyGridView gridview_Yijia;
    private MyGridView gridview_Zhongchao;
    private MyGridView gridview_Zhongjia;
    private MyGridView gridview_Youxi;
    private MyGridView gridview_Qiuyuan;
    private MyGridView gridview_Qita;
    private LeftCircleAdapter adapter;
    private LeftCircleAdapter adapter1;
    private LeftCircleAdapter adapter2;
    private LeftCircleAdapter adapter3;
    private LeftCircleAdapter adapter4;
    private LeftCircleAdapter adapter5;
    private LeftCircleAdapter adapter6;
    private LeftCircleAdapter adapter7;
    private LeftCircleAdapter adapter8;
    private LeftCircleAdapter adapter9;
    private ArrayList<League> list;
    private ArrayList<League> list1;
    private ArrayList<League> list2;
    private ArrayList<League> list3;
    private ArrayList<League> list4;
    private ArrayList<League> list5;
    private ArrayList<League> list6;
    private ArrayList<League> list7;
    private ArrayList<League> list8;
    private ArrayList<League> list9;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue= Volley.newRequestQueue(getActivity());
        totalList=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadData();
        return inflater.inflate(R.layout.fragment_leftcircle,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridview_Xingqu = ((MyGridView) view.findViewById(R.id.gridview_xingqu));

        list = new ArrayList<>();
        adapter = new LeftCircleAdapter(list,getActivity(),mQueue);
        gridview_Xingqu.setAdapter(adapter);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Yingchao = ((MyGridView) view.findViewById(R.id.gridview_yingchao));
        list1 = new ArrayList<>();
        adapter1 = new LeftCircleAdapter(list1,getActivity(),mQueue);
        gridview_Yingchao.setAdapter(adapter1);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Xijia = ((MyGridView) view.findViewById(R.id.gridview_xijia));
        list2 = new ArrayList<>();
        adapter2 = new LeftCircleAdapter(list2,getActivity(),mQueue);
        gridview_Xijia.setAdapter(adapter2);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Dejia = ((MyGridView) view.findViewById(R.id.gridview_dejia));
        list3 = new ArrayList<>();
        adapter3 = new LeftCircleAdapter(list3,getActivity(),mQueue);
        gridview_Dejia.setAdapter(adapter3);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Yijia = ((MyGridView) view.findViewById(R.id.gridview_yijia));
        list4 = new ArrayList<>();
        adapter4 = new LeftCircleAdapter(list4,getActivity(),mQueue);
        gridview_Yijia.setAdapter(adapter4);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Zhongchao = ((MyGridView) view.findViewById(R.id.gridview_zhongchao));
        list5 = new ArrayList<>();
        adapter5 = new LeftCircleAdapter(list5,getActivity(),mQueue);
        gridview_Zhongchao.setAdapter(adapter5);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Zhongjia = ((MyGridView) view.findViewById(R.id.gridview_zhongjia));
        list6 = new ArrayList<>();
        adapter6 = new LeftCircleAdapter(list6,getActivity(),mQueue);
        gridview_Zhongjia.setAdapter(adapter6);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Youxi = ((MyGridView) view.findViewById(R.id.gridview_youxi));
        list7 = new ArrayList<>();
        adapter7 = new LeftCircleAdapter(list7,getActivity(),mQueue);
        gridview_Youxi.setAdapter(adapter7);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Qiuyuan = ((MyGridView) view.findViewById(R.id.gridview_qiuyuan));
        list8 = new ArrayList<>();
        adapter8 = new LeftCircleAdapter(list8,getActivity(),mQueue);
        gridview_Qiuyuan.setAdapter(adapter8);
        gridview_Xingqu.setOnItemClickListener(this);

        gridview_Qita = ((MyGridView) view.findViewById(R.id.gridview_qita));
        list9 = new ArrayList<>();
        adapter9 = new LeftCircleAdapter(list9,getActivity(),mQueue);
        gridview_Qita.setAdapter(adapter9);
        gridview_Xingqu.setOnItemClickListener(this);



    }

    //配置数据源
    public void loadData(){
        stringRequest= new StringRequest(CircleURL.circleURL,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            List<Groups>result= JsonToList.circleJsonToList(s);
                if (list != null) {
                    totalList.addAll(result);
                    list.addAll(totalList.get(0).getList());
                    adapter.notifyDataSetChanged();
                    list1.addAll(totalList.get(1).getList());
                    adapter1.notifyDataSetChanged();
                    list2.addAll(totalList.get(2).getList());
                    adapter2.notifyDataSetChanged();
                    list3.addAll(totalList.get(3).getList());
                    adapter3.notifyDataSetChanged();
                    list4.addAll(totalList.get(4).getList());
                    adapter4.notifyDataSetChanged();
                    list5.addAll(totalList.get(5).getList());
                    adapter5.notifyDataSetChanged();
                    list6.addAll(totalList.get(6).getList());
                    adapter6.notifyDataSetChanged();
                    list7.addAll(totalList.get(7).getList());
                    adapter7.notifyDataSetChanged();
                    list8.addAll(totalList.get(8).getList());
                    adapter8.notifyDataSetChanged();
                    list9.addAll(totalList.get(9).getList());
                    adapter9.notifyDataSetChanged();

                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"网络访问失败！！",Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(stringRequest);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = ((League) adapterView.getItemAtPosition(i)).getId();
        Intent intent=new Intent(getActivity(),CircleDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
