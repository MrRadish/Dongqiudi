package com.ds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ds.adapter.DataListViewAdapter_integral;
import com.ds.adapter.DataListViewAdapter_race;
import com.ds.adapter.DataListViewAdapter_secondary;
import com.ds.adapter.DataListViewAdapter_shooter;
import com.ds.dongqiudi.R;
import com.ds.entity.DataIntegral;
import com.ds.entity.DataMatche;
import com.ds.entity.DataShooter;
import com.ds.utils.DataUrl;
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
 * Created by aaa on 15-4-1.
 */
public class DataViewpagerExtrafragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup radioGroup_detail;
    private ListView listView;
    private List<DataIntegral> list;
    private List<DataShooter> listShooter;
    private List<DataMatche> listRace;
    //接口
    private String integralUrl;
    private String shooterUrl;
    private String raceUrl;
    //listview适配器
    private DataListViewAdapter_integral adapter_integral;
    private DataListViewAdapter_shooter adapter_shooter;
    private DataListViewAdapter_race adapter_race;
    private HttpUtils httpUtils;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id=getArguments().getInt("id");
        switch (id)
        {
            //中甲 >
            case 7:
                integralUrl= DataUrl.dataZjUrl_integral;
                shooterUrl= DataUrl.dataZjUrl_shooter;
                raceUrl=DataUrl.dataZjUrl_race;
                break;
            //葡超 >
            case 12:
                integralUrl= DataUrl.dataPcUrl_integral;
                shooterUrl= DataUrl.dataPcUrl_shooter;
                raceUrl=DataUrl.dataPcUrl_race;
                break;
            //土超 >
            case 14:
                integralUrl= DataUrl.dataTcUrl_integral;
                shooterUrl= DataUrl.dataTcUrl_shooter;
                raceUrl=DataUrl.dataTcUrl_race;
                break;


        }
        list=new ArrayList<DataIntegral>();
        listShooter=new ArrayList<DataShooter>();
        listRace=new ArrayList<DataMatche>();
        httpUtils=new HttpUtils();
        //适配器
        adapter_integral = new DataListViewAdapter_integral(list,getActivity());
        adapter_shooter=new DataListViewAdapter_shooter(listShooter,getActivity());
        adapter_race=new DataListViewAdapter_race(listRace,getActivity());


    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dataviewpagerextrafragment,container,false);

        listView= (ListView) view.findViewById(R.id.listView_data_jifeng);
        listView.setAdapter(adapter_integral);
        radioGroup_detail= (RadioGroup) view.findViewById(R.id.data_rg_detail);
        View v=radioGroup_detail.getChildAt(0);
        if(v!=null&&v instanceof RadioButton){
            RadioButton rf=(RadioButton)v;
            rf.setChecked(true);
            DataViewpagerfragment_integral datafragment=new DataViewpagerfragment_integral();
            repalceFragment(datafragment);
            //网络加载/

            getIntergralData(integralUrl);
            listView.setAdapter(adapter_integral);

        }
        radioGroup_detail.setOnCheckedChangeListener(this);

        return  view;
}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 对单选按钮组的监听事件
     * @param radioGroup
     * @param i
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i)
        {
            case R.id.data_view_jifeng:

                DataViewpagerfragment_integral datafragment=new DataViewpagerfragment_integral();
                repalceFragment(datafragment);
                //网络加载/

                getIntergralData(integralUrl);
                listView.setAdapter(adapter_integral);
                break;
            case R.id.data_view_sheshou:
                DataViewpagerfragment_shooter shooter=new DataViewpagerfragment_shooter();
                repalceFragment(shooter);
                listView.setAdapter(adapter_shooter);
                getShooterData(shooterUrl);
                break;

            case R.id.data_view_saicheng:
                DataViewpagerfragment_race race=new DataViewpagerfragment_race();
                repalceFragment(race);
                listView.setAdapter(adapter_race);
                getRaceData(raceUrl);
                break;
        }
    }
    /**
     *用于替换碎片的方法
     * @param datafragment
     */
    public void repalceFragment(Fragment datafragment)
    {
        FragmentManager manager_data=getChildFragmentManager();
        FragmentTransaction transaction_jifeng=manager_data.beginTransaction();
        transaction_jifeng.replace(R.id.data_fragment_viewpager_container,datafragment);
        //允许用户按返回键后返回到替换之前的fragment
        transaction_jifeng.addToBackStack(null);
        transaction_jifeng.commit();
    }
    /**
     * 积分的数据源
     * @param url
     */
    public void getIntergralData(String url){
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
                            integral.setRanking(jsonObject_rank.getString("rank"));
                            integral.setImg_id(jsonObject_rank.getString("team_id"));
                            integral.setTeam(jsonObject_rank.getString("club_name"));
                            integral.setMatcheTotal(jsonObject_rank.getInt("matches_total"));
                            integral.setVictory(jsonObject_rank.getInt("matches_won"));
                            integral.setDraw(jsonObject_rank.getInt("matches_draw"));
                            integral.setLost(jsonObject_rank.getInt("matches_lost"));
                            integral.setProcess(jsonObject_rank.getInt("goals_pro"));
                            integral.setLose(jsonObject_rank.getInt("goals_against"));
                            integral.setPoint(jsonObject_rank.getInt("points"));
                            list.add(integral);
                            Log.d("DataUtils", list.toString());
                        }
                        adapter_integral.add(list);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }

    /**
     * 射手的数据源
     * @param url
     */
    public void getShooterData(String url){
        httpUtils.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                try {
                    JSONArray jsonArray=new JSONArray(objectResponseInfo.result);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                        DataShooter shooter=new DataShooter();
                        shooter.setTeam_id(jsonObject.getString("team_id"));
                        shooter.setName(jsonObject.getString("name"));
                        shooter.setTeam(jsonObject.getString("team_name"));
                        shooter.setProcessNum(jsonObject.getInt("count"));
                        listShooter.add(shooter);
                        Log.d("--->",listShooter.toString());
                    }
                    adapter_shooter.add(listShooter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }


    //    比赛的数据源
    public void getRaceData(String url){
        httpUtils.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                try {
                    JSONObject jsonObject=new JSONObject(objectResponseInfo.result);
                    JSONArray jsonArray= (JSONArray) jsonObject.get("matches");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject_race= (JSONObject) jsonArray.get(i);
                        DataMatche matche=new DataMatche();
                        matche.setDate(jsonObject_race.getString("date_utc"));
                        matche.setTime(jsonObject_race.getString("time_utc"));
                        matche.setaName(jsonObject_race.getString("team_A_name"));
                        matche.setaImage(jsonObject_race.getString("team_A_id"));
                        matche.setaScore(jsonObject_race.getString("fs_A"));
                        matche.setbScore(jsonObject_race.getString("fs_B"));
                        matche.setBimage(jsonObject_race.getString("team_B_id"));
                        matche.setbName(jsonObject_race.getString("team_B_name"));
                        listRace.add(matche);
                    }
                    adapter_race.add(listRace);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }

}
