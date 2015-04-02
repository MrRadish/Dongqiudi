package com.ds.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.dongqiudi.R;
import com.ds.utils.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class newsGameDetailFragment extends Fragment {
    private WebView main_fragment_newsWebView;
    private String match_id;
    private String url;
    private RequestQueue mRequestQueue;
    private String load;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        Bundle b = getArguments();
        match_id = b.getString("id");
        url = Urls.news + match_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gamedetail_news,container,false);
        main_fragment_newsWebView = ((WebView) view.findViewById(R.id.main_fragment_newsWebView));
        initLoad();
        main_fragment_newsWebView.loadDataWithBaseURL(null,load,"text/html","utf-8",null);
        main_fragment_newsWebView.getSettings().setJavaScriptEnabled(true);
        main_fragment_newsWebView.setWebChromeClient(new WebChromeClient());
        main_fragment_newsWebView.setWebViewClient(new WebViewClient());
        return view;
    }

    public void initLoad(){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray array = jsonObject.getJSONArray("matchVideo");
                    JSONObject jsons = array.getJSONObject(0);
                    load = jsons.getString("appcontent");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "网络连接出错", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRequestQueue.add(jsonRequest);
    }

}
