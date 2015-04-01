package com.ds.adapter;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ds.dongqiudi.R;
import com.ds.entity.Importance;

import org.apache.http.entity.StringEntity;

import java.util.List;

/**
 * Created by aaa on 15-3-26.
 */
public class MainListViewAdapter extends BaseAdapter {
    private List<Importance> list;
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public MainListViewAdapter(List<Importance> list, Context context,
                               RequestQueue mRequestQueue,ImageLoader.ImageCache imageCache
                               ) {
        this.list = list;
        this.context = context;
        this.mRequestQueue = mRequestQueue;
        mImageLoader = new ImageLoader(mRequestQueue,imageCache);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.mylistview_item,viewGroup,false);
            holder.team_A_name = (TextView) view.findViewById(R.id.team_A_name);
            holder.team_B_name = (TextView) view.findViewById(R.id.team_B_name);
            holder.date_utc = ((TextView) view.findViewById(R.id.date_utc));
            holder.fav = (ImageView) view.findViewById(R.id.fav);
            holder.Fs = ((TextView) view.findViewById(R.id.Fs));
            holder.left_image = ((ImageView) view.findViewById(R.id.mylistview_item_left_image));
            holder.right_image = ((ImageView) view.findViewById(R.id.mylistview_item_right_image));
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
            mImageLoader.get("http://img.dongqiudi.com/data/pic/"+list.get(i).getTeam_A_id()+".png", mImageLoader
                .getImageListener(holder.left_image, R.drawable.team_icon_null,
                        R.drawable.team_icon_null), 0, 0);
            mImageLoader.get("http://img.dongqiudi.com/data/pic/"+list.get(i).getTeam_B_id()+".png",mImageLoader
            .getImageListener(holder.right_image,R.drawable.team_icon_null,
                    R.drawable.team_icon_null),0,0);
            holder.team_A_name.setText(list.get(i).getTeam_A_name());
            holder.team_B_name.setText(list.get(i).getTeam_B_name());
            holder.date_utc.setText(list.get(i).getDate_utc()+" "+list.get(i).getCompetition_name());
        if (!TextUtils.isEmpty(list.get(i).getFs_A())) {
            holder.Fs.setText(list.get(i).getFs_A() + " " + "-" + " " + list.get(i).getFs_B());
        }else {
            holder.Fs.setVisibility(View.GONE);
            holder.fav.setVisibility(View.VISIBLE);
        }
        return view;
    }

    class ViewHolder{
        private ImageView right_image;
        private ImageView left_image;
        private TextView team_A_name;
        private TextView team_B_name;
        private TextView date_utc;
        private TextView Fs;
        private ImageView fav;
    }
}
