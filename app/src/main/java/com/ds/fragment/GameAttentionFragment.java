package com.ds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.MainListViewAdapter;
import com.ds.dongqiudi.R;
import com.ds.entity.Importance;
import com.ds.utils.MyBitmapCache;
import com.ds.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aaa on 15-3-25.
 */
public class GameAttentionFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{
    private List<Importance> totalist = new ArrayList<>();
    private boolean isRequest = false;
    private MainListViewAdapter adapter;
    private ImageLoader.ImageCache imageCache = MyBitmapCache.getInstance();
    private String url;
    private RequestQueue mRequestQueue;
    private PullToRefreshListView MyList;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                MyList.onRefreshComplete();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = Urls.attention + Urls.initDate();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        adapter = new MainListViewAdapter(totalist, getActivity(),mRequestQueue,imageCache
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_main, container, false);
        View EmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.mylistview_empty,null,false);
        MyList = ((PullToRefreshListView) view.findViewById(R.id.MyList));
        MyList.setAdapter(adapter);
        MyList.getRefreshableView().setDivider(null);
        MyList.setMode(PullToRefreshBase.Mode.BOTH);
        MyList.getRefreshableView().setSelector(android.R.color.transparent);
        MyList.setOnRefreshListener(this);
        MyList.setEmptyView(EmptyView);
        MyList.setOnItemClickListener(this);
        jsonData();
        return view;
    }

    public void jsonData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Importance> ImportanceList = new ArrayList<>();
                            JSONObject object = response;
                            JSONArray list = object.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                Importance importance = new Importance();
                                JSONObject myjsons = list.getJSONObject(i);
                                importance.setTeam_A_id(myjsons.getString("team_A_id"));
                                importance.setTeam_B_id(myjsons.getString("team_B_id"));
                                importance.setTeam_A_name(myjsons.getString("team_A_name"));
                                importance.setTeam_B_name(myjsons.getString("team_B_name"));
                                importance.setCompetition_name(myjsons.getString("competition_name"));
                                importance.setDate_utc(myjsons.getString("date_utc"));
                                importance.setFs_A(myjsons.getString("fs_A"));
                                importance.setFs_B(myjsons.getString("fs_B"));
                                importance.setMatch_id(myjsons.getString("match_id"));
                                ImportanceList.add(importance);
                            }
                            totalist.addAll(ImportanceList);
                            adapter.notifyDataSetChanged();
                            isRequest = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        totalist.clear();
        jsonData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Log.i("Info",""+url.indexOf("-"));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("match_id",totalist.get(i - 1).getMatch_id());
        bundle.putString("team_A_id",totalist.get(i - 1).getTeam_A_id());
        bundle.putString("team_B_id",totalist.get(i - 1).getTeam_B_id());
        bundle.putString("competition_name",totalist.get(i - 1).getCompetition_name());
        bundle.putString("fs_A",totalist.get(i - 1).getFs_A());
        bundle.putString("fs_B",totalist.get(i - 1).getFs_B());
        bundle.putString("date_utc",totalist.get(i - 1).getDate_utc());
        intent.putExtras(bundle);
        intent.setClass(getActivity(), GameDetail_one.class);
        startActivity(intent);
    }
}
