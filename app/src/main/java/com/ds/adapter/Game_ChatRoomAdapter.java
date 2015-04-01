package com.ds.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.ds.dongqiudi.R;
import com.ds.entity.ChatContent;
import com.ds.utils.MyBitmapCache;

import java.util.List;

/**
 * Created by aaa on 15-3-31.
 */
public class Game_ChatRoomAdapter extends BaseAdapter{
    private Context context;
    private List<ChatContent> list;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public Game_ChatRoomAdapter(RequestQueue mRequestQueue, Context context, List<ChatContent> list) {
        this.mRequestQueue = mRequestQueue;
        this.context = context;
        this.list = list;
        this.mImageLoader = new ImageLoader(mRequestQueue, MyBitmapCache.getInstance());
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
            view = LayoutInflater.from(context).inflate(R.layout.chatroom_item,viewGroup,false);
            holder = new ViewHolder();
            holder.Chat_avatar = ((ImageView) view.findViewById(R.id.Chat_avatar));
            holder.Chat_message = ((TextView) view.findViewById(R.id.Chat_message));
            holder.Chat_username = ((TextView) view.findViewById(R.id.Chat_username));
            view.setTag(holder);
        }else {
            holder = ((ViewHolder) view.getTag());
        }
        mImageLoader.get(list.get(i).getAvatar(),ImageLoader.getImageListener(holder.Chat_avatar,
                R.drawable.myicon,R.drawable.myicon),0,0);
        holder.Chat_username.setText(list.get(i).getUsername());
        holder.Chat_message.setText(list.get(i).getMessage());
        return view;
    }
    class ViewHolder{
        private ImageView Chat_avatar;
        private TextView Chat_username;
        private TextView Chat_message;
    }
}
