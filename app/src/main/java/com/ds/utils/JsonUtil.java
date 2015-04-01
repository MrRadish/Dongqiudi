package com.ds.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ds.entity.ArticleHead;
import com.ds.entity.MainArticle;
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
* Created by Administrator on 2015/3/26.
*/
public class JsonUtil {
    private  static HttpUtils httpUtils;

    /**
     * 获取首页文章的json数据
     * @param jsonUrl
     * @return
     */
    public static List<MainArticle> getMainJson(String jsonUrl ) {
        Log.d("JsonUtil","开始解析json数据");
        List<MainArticle> data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonUrl);
            String prev = jsonObject.getString("prev");
            JSONArray objs = jsonObject.getJSONArray("articles");
            for (int i = 0; i < objs.length(); i++) {
                MainArticle article = new MainArticle();
                article.setNextUrl(jsonObject.getString("next"));
                JSONObject obj = (JSONObject) objs.get(i);
                article.setId(obj.getInt("id"));
                article.setTitle(obj.getString("title"));
                article.setDescription(obj.optString("description"));
                article.setComments(obj.getInt("comments_total"));
                article.setShareUrl(obj.getString("share"));
                article.setImgUrl(obj.getString("thumb"));
                article.setPublishTime(obj.getString("published_at"));
                article.setArticleUrl(obj.getString("url"));
                article.setLabel(obj.optString("label"));
                article.setLabelColor(obj.optString("label_color"));
//                        JSONObject sbj=obj.optJSONObject("album");
//                        article.setSbjImgCount(sbj.optInt("total"));
//                        JSONArray sbjarray=sbj.getJSONArray("pics");
//                        List<String> list1=new ArrayList<String>();
//                        for(i=0;i<sbjarray.length();i++){
//                            list1.add(sbjarray.get(i).toString());
//                           article.setSbjImgUrl(list1);
//                        }
                data.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

}
//                    try {
//                        //缓存数据
//                        DBHelper.getUtils().saveOrUpdateAll(list);
//                    } catch (DbException e) {
//                        e.printStackTrace();
//                    }