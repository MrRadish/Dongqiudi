package com.ds.dongqiudi;



import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ds.fragment.CircleFragmennt;
import com.ds.fragment.Datafragment;
import com.ds.fragment.GameFragment;
import com.ds.fragment.Mainfragment;
import com.ds.utils.BitmapHelper;
import com.ds.utils.DBHelper;


public class MainActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup btmGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据库
        DBHelper.initDbUtils(this);
        //初始化UI
        initUI(this);
        //初始化Bitmaputils
        BitmapHelper.initUtils(this);
    }

    /**
     * 初始化UI
     */
    private void initUI(Context context){
//        refresh= ((PullToRefreshListView)findViewById(R.id.refresh));
        btmGroup= ((RadioGroup)findViewById(R.id.btm_rd_group));
        btmGroup.setOnCheckedChangeListener(this);
        //默认第一个选中
        ((RadioButton) btmGroup.getChildAt(0)).setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {


        if(radioGroup!=null){
            switch (i){
                case R.id.rd_main:
                    //首页fragment
                    Mainfragment mainfragment=new Mainfragment();
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.main_fragment_container,mainfragment);
                    //允许用户按返回键后返回到替换之前的fragment
//                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.rd_game:
                    //比赛fragment
                    GameFragment gameFragment=new GameFragment();
                    FragmentManager manager1=getSupportFragmentManager();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    transaction1.replace(R.id.main_fragment_container,gameFragment);
                    //允许用户按返回键后返回到替换之前的fragment
                    transaction1.addToBackStack(null);
                    transaction1.commit();
                    break;
                case R.id.rd_login:
                    //登录fragment(暂时不写)
                    break;
                case R.id.rd_people:
                    //圈子fragment
                    CircleFragmennt fragmennt_Circle=new CircleFragmennt();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment_container,fragmennt_Circle)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.rd_data:
                    //数据fragment
                    Datafragment datafragment=new Datafragment();
                    FragmentManager manager_data=getSupportFragmentManager();
                    FragmentTransaction transaction_data=manager_data.beginTransaction();
                    transaction_data.replace(R.id.main_fragment_container,datafragment);
                    //允许用户按返回键后返回到替换之前的fragment
                    transaction_data.addToBackStack(null);
                    transaction_data.commit();
                    break;
            }
        }
    }
}
