package com.ds.dongqiudi;


import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.entity.Datateam;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class TeamActivity extends ActionBarActivity implements View.OnClickListener{
    private String headurl="http://data.ballpo.com/v2/data/team/info/sample/433";
private ImageView teamBack;
    private ImageView teamImg;
    private ImageView countryImg;
    private TextView teamName;
    private TextView teamEnName;
    private HttpUtils httpUtils;
    private BitmapUtils bitmapUtils;
    private Datateam team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_team);
        init();

    }
    public void init()
    {
        bitmapUtils=new BitmapUtils(this);

        teamBack= (ImageView) findViewById(R.id.team_back);
        teamBack.setImageResource(R.drawable.back_btn_bg);
        teamBack.setOnClickListener(this);
        getTeamData(headurl);
        teamImg= (ImageView) findViewById(R.id.team_img_log);
        countryImg= (ImageView) findViewById(R.id.imageView_country);
        teamName= (TextView) findViewById(R.id.team_text_name);
        teamEnName= (TextView) findViewById(R.id.team_text_spelling);
//        teamName.setText(team.getTeamName());
//        teamEnName.setText(team.getTeamSpelling());
//        bitmapUtils.display(teamImg,team.getTeamImg());
//        bitmapUtils.display(countryImg,team.getCountryImg());
    }
    public void getTeamData( String url)
    {
        httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                try {
                    JSONObject jsonObject=new JSONObject(objectResponseInfo.result);
                    team=new Datateam();
                    team.setTeamName(jsonObject.getString("team_name"));
                    Log.d("=======>",team.getTeamName()+"=======");
                    team.setTeamSpelling(jsonObject.getString("team_en_name"));
                    team.setTeamImg(jsonObject.getString("team_img"));
                    team.setCountryImg(jsonObject.getString("country_img"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
