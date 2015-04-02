package com.ds.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ds.dongqiudi.R;
import com.ds.entity.ArticleHead;
import com.ds.utils.BitmapHelper;
import com.ds.view.MyViewPager;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

/**
 * 首页头部fragment
 * Created by Administrator on 2015/3/26.
 */
public class MainListHeadFragment extends Fragment {

    private ImageView bgImag;
    private TextView btTxt;
    private ArticleHead head;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        head = (ArticleHead) getArguments().getSerializable("entity");
//        Log.d("HeadFragment---------------------",head.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_list_head_item, container, false);
        bgImag = (ImageView) view.findViewById(R.id.main_list_head_item_imageView);
        btTxt = ((TextView) view.findViewById(R.id.main_list_head_item_title));
        btTxt.setText(head.getTitle());
        BitmapHelper.getBitmapUtils().display(bgImag,head.getImgUrl(),new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                    imageView.setImageDrawable(drawable);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        head = (ArticleHead) getArguments().getSerializable("entity");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
