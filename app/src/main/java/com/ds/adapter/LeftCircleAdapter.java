package com.ds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.ds.dongqiudi.R;
import com.ds.entity.League;
import com.ds.utils.MyBitmapCache;

import java.util.List;

/**
 * Created by aaa on 15-4-2.
 */
public class LeftCircleAdapter extends BaseAdapter{
    private List<League>list;
    private Context context;
    private ImageLoader mLoader;

    public LeftCircleAdapter(List<League> list, Context context,RequestQueue mQueue) {
        this.list = list;
        this.context = context;
        mLoader=new ImageLoader(mQueue, MyBitmapCache.getInstance());
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
        if (view == null) {
            view= LayoutInflater.from(context).inflate(R.layout.fragment_leftcircle_item,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder mHolder= (ViewHolder) view.getTag();
        League league = list.get(i);
        if (league != null) {
            mHolder.leagueName.setText(league.getTitle());
            mHolder.topicCount.setText(""+league.getTopic_total());
            mHolder.memberCount.setText(""+league.getJoin_user_total());

            mLoader.get(league.getThumb(),ImageLoader.getImageListener(mHolder.avatar,
                    R.drawable.team_icon_null,R.drawable.team_icon_null));
        }

        return view;
    }

    public static class ViewHolder{

        private TextView leagueName;
        private TextView memberCount;
        private TextView topicCount;
        private ImageView avatar;

        public ViewHolder(View view){
            leagueName= ((TextView) view.findViewById(R.id.textview_leftcircle_item_leaguename));
            memberCount= ((TextView) view.findViewById(R.id.textview_leftcircle_item_membercount));
            topicCount= ((TextView) view.findViewById(R.id.textview_leftcircle_item_topicscount));
            avatar= ((ImageView) view.findViewById(R.id.imageview_leftcircle_thumb));
        }
    }
}
