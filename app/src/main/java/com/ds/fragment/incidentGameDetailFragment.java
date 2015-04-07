package com.ds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.Game_EventAdapter;
import com.ds.dongqiudi.R;
import com.ds.entity.Events;
import com.ds.entity.Incident;
import com.ds.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class incidentGameDetailFragment extends Fragment {
    private String match_id;
    private ListView incident_ListView;
    private RequestQueue mRequestQueue;
    private String mintes = "0";
    private List<Events> eventsList = new ArrayList<>();
    private Game_EventAdapter adapter;
    private String url;
    private String A;
    private float scale;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        match_id = getArguments().getString("id");
        A = getArguments().getString("A");
        url = Urls.incident + match_id;
        mRequestQueue = Volley.newRequestQueue(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail_incident,container,false);
        adapter = new Game_EventAdapter(getActivity(),eventsList,A);
        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.mylistview_empty,null,false);
        incident_ListView = ((ListView) view.findViewById(R.id.incident_ListView));
        incident_ListView.setEmptyView(empty);
        incident_ListView.setAdapter(adapter);
        incident_ListView.setDividerHeight(0);
        initJson();
        return view;
    }

    public void initJson(){
        Log.i("Info","网址："+url);
        JsonObjectRequest mJsonRequest = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Events myEvent = null;
                            JSONArray events = jsonObject.getJSONArray("events");
                            for (int i = 0;i<events.length();i++) {
                                JSONObject Eventsjson = events.getJSONObject(i);
                                Incident incident = null;
                                Log.i("Info",mintes+"这里是时间哦");
                                if (!Eventsjson.getString("minute").equals(mintes)){
                                    if (myEvent!=null){
                                        Log.i("Info",myEvent.getMinute()+"得到的时间在这里");
                                        eventsList.add(myEvent);
                                    }
                                    incident = new Incident();
                                    myEvent = new Events();
                                    myEvent.setTeam_id(Eventsjson.getString("team_id"));
                                    JSONObject evena = Eventsjson.optJSONObject("eventA");
                                    incident.setCode(evena.getString("code"));
                                    incident.setPerson(evena.getString("person"));
                                    incident.setPerson_id(evena.getString("person_id"));
                                    myEvent.addlist(incident);
                                    JSONObject evenb = Eventsjson.optJSONObject("eventB");
                                    if (evenb!=null){
                                        incident = new Incident();
                                        incident.setCode(evenb.getString("code"));
                                        incident.setPerson(evenb.getString("person"));
                                        incident.setPerson_id(evenb.getString("person_id"));
                                        myEvent.addlist(incident);
                                    }
                                        mintes = Eventsjson.getString("minute");
                                        myEvent.setMinute(mintes);
                                    if (i == events.length() - 1){
                                        eventsList.add(myEvent);
                                    }
                                }else {
                                    incident = new Incident();
                                    JSONObject evena = Eventsjson.optJSONObject("eventA");
                                    incident.setCode(evena.getString("code"));
                                    incident.setPerson(evena.getString("person"));
                                    incident.setPerson_id(evena.getString("person_id"));
                                    myEvent.addlist(incident);
                                    JSONObject evenb = Eventsjson.optJSONObject("eventB");
                                    if (evenb!=null){
                                        incident = new Incident();
                                        incident.setCode(evenb.getString("code"));
                                        incident.setPerson(evenb.getString("person"));
                                        incident.setPerson_id(evenb.getString("person_id"));
                                        myEvent.addlist(incident);
                                    }
                                      if (i == events.length() - 1){
                                          eventsList.add(myEvent);
                                      }
//                                    eventsList.add(myEvent);
                                }

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(mJsonRequest);

    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
