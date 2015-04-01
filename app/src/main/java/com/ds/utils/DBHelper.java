package com.ds.utils;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

/**
 * Created by Administrator on 2015/4/1.
 */
public class DBHelper {
    private static DbUtils utils;
    public static void initDbUtils(Context context){
        utils=DbUtils.create(context,"dongqiudi.db");
        //代开debug开关
        utils.configDebug(true);
        //打开事务开关
        utils.configAllowTransaction(true);
    }

    public static DbUtils getUtils() {
        return utils;
    }
}
