package com.ds.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.ds.dongqiudi.R;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;

/**
 * Created by Administrator on 2015/3/26.
 */
public class BitmapHelper {
    private static BitmapUtils bitmapUtils;
    public static void initUtils(Context context){
        bitmapUtils=new BitmapUtils(context,
                new File(Environment.getExternalStorageDirectory(),"dongqiudi").getAbsolutePath(),0.25f);
        //默认显示的图片
        bitmapUtils.configDefaultLoadingImage(R.drawable.message_pic_bg);
        //加载失败时显示的图片
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.icon_null);
        //图片显示的最大尺寸
        bitmapUtils.configDefaultBitmapMaxSize(80,80);
        //图片的缓存时间
        bitmapUtils.configDefaultCacheExpiry(1000*60*60*24);
        //网络连接超时时间
        bitmapUtils.configDefaultConnectTimeout(5000);
        //图片读取超时时间
        bitmapUtils.configDefaultReadTimeout(10000);
    }

    public static BitmapUtils getBitmapUtils() {
        return bitmapUtils;
    }
}
