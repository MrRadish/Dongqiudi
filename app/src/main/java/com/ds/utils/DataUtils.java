package com.ds.utils;

import android.util.Log;

import com.ds.entity.DataIntegral;
import com.lidroid.xutils.HttpUtils;
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
 * 网络访问获得一个json数据
 * Created by aaa on 15-3-27.
 */
public class DataUtils {
    private static List<DataIntegral> list;
    public static List<DataIntegral> getData(String url)
    {
        list=new ArrayList<DataIntegral>();
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                try {
                    JSONArray jsonArray=new JSONArray(objectResponseInfo.result);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                        JSONArray jsonArray_integral=jsonObject.getJSONArray("rankings");
                        for (int j=0;j<jsonArray_integral.length();j++)
                        {
                            JSONObject jsonObject_rank= (JSONObject) jsonArray_integral.get(j);
                            DataIntegral integral=new DataIntegral();
                            integral.setTeam(jsonObject_rank.getString("club_name"));
                            integral.setMatcheTotal(jsonObject_rank.getInt("matches_total"));
                            integral.setVictory(jsonObject_rank.getInt("matches_won"));
                            integral.setDraw(jsonObject_rank.getInt("matches_draw"));
                            integral.setLost(jsonObject_rank.getInt("matches_lost"));
                            integral.setProcess(jsonObject_rank.getInt("goals_pro"));
                            integral.setLose(jsonObject_rank.getInt("goals_against"));
                            integral.setPoint(jsonObject_rank.getInt("points"));
                            list.add(integral);
                            Log.d("DataUtils",list.toString());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
        return list;
    }
}
