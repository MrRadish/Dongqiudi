package com.ds.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.Game_ChatRoomAdapter;
import com.ds.dongqiudi.R;
import com.ds.entity.ChatContent;
import com.ds.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class chatGameDetailFragment extends Fragment {
    private String id;
    private RequestQueue mRequestQueue;
    private String chatRoomNameUrl;
    private String chatRoomContentUrl;
    private int page = 20;
    private PullToRefreshListView Chat_ListView;
    private TextView Chat_ListView_Empty;
    private List<ChatContent> mainList = new ArrayList<>();
    private Game_ChatRoomAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
        chatRoomNameUrl = Urls.chatRoom + id + "?type=match";
        mRequestQueue = Volley.newRequestQueue(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gamedetail_chat,container,false);
        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.chatroom_empty,null,false);
        adapter = new Game_ChatRoomAdapter(mRequestQueue,getActivity(),mainList);
        Chat_ListView = ((PullToRefreshListView) view.findViewById(R.id.Chat_ListView));
        Chat_ListView.setAdapter(adapter);
        Chat_ListView.setEmptyView(empty);
        return view;
    }

    public void initjson(){
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(chatRoomNameUrl,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject != null){
                    try {
                        chatRoomContentUrl = Urls.chatContent + jsonObject.getString("chatroom_id") +
                               "&limit=" + page;
                        if (chatRoomContentUrl!=null){
                            initChatContent();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"数据访问出错",Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(mJsonObjectRequest);

    }

    public void initChatContent(){
        StringRequest mStringRequest = new StringRequest(Request.Method.GET,chatRoomContentUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray array = new JSONArray(s);
                    for (int i = 0; i < array.length(); i++){
                        ChatContent content = new ChatContent();
                        JSONObject json = array.getJSONObject(i);
                        content.setMessage(json.getString("message"));
                        content.setUsername(json.getString("username"));
                        content.setAvatar(json.getString("avatar"));
                        mainList.add(content);
                    }
                        adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(mStringRequest);
    }
}
