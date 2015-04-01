package com.ds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.ds.dongqiudi.R;
import com.ds.entity.LeftCircleItem;
import com.ds.utils.MyBitmapCache;

import java.util.List;

/**
 * Created by aaa on 15-4-1.
 */
public class LeftCircleAdapter extends RecyclerView.Adapter<LeftCircleAdapter.ViewHolder> {
    private List<LeftCircleItem>list;
    private Context context;
    private ImageLoader mLoader;

    public LeftCircleAdapter(List<LeftCircleItem> list, Context context,
     RequestQueue mQueue) {
        this.list = list;
        this.context = context;
        mLoader=new ImageLoader(mQueue, MyBitmapCache.getInstance());
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_leftcircle_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder mHoder, int i) {
        LeftCircleItem item = list.get(i);

        if (item != null) {

                mHoder.groupName.setText(item.getGroupName());


            if (item.getLeague1() != null) {
                mHoder.leagueName1.setText(item.getLeague1().getTitle());
                mHoder.leagueTopicCount1.setText(""+item.getLeague1().getTopic_total());
                mHoder.leagueMemberCount1.setText(""+item.getLeague1().getJoin_user_total());
                mLoader.get(item.getLeague1().getThumb(),mLoader.getImageListener(mHoder.leagueThumb1,
                        R.drawable.product_fail, R.drawable.defaultcovers),0,0);
            }
            if (item.getLeague2() != null) {
                mHoder.leagueThumb2.setVisibility(View.VISIBLE);
                mHoder.relativeLayout.setVisibility(View.VISIBLE);
                mHoder.leagueName2.setText(item.getLeague2().getTitle());
                mHoder.leagueTopicCount2.setText(""+item.getLeague2().getTopic_total());
                mHoder.leagueMemberCount2.setText(""+item.getLeague2().getJoin_user_total());
                mLoader.get(item.getLeague2().getThumb(),mLoader.getImageListener(mHoder.leagueThumb2,
                        R.drawable.product_fail, R.drawable.defaultcovers),0,0);

            }

        }

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView groupName;
        private TextView leagueName1;
        private TextView leagueMemberCount1;
        private TextView leagueTopicCount1;
        private ImageView leagueThumb1;
        private TextView leagueName2;
        private TextView leagueMemberCount2;
        private TextView leagueTopicCount2;
        private ImageView leagueThumb2;
        private ImageView leagueMembersIcon2;
        private ImageView leagueTopicsIcon2;
        private LinearLayout leftLayout;
        private LinearLayout rightLayout;
        private RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            super(view);

            groupName= ((TextView) view.findViewById(R.id.textview_leftcircle_item_groupname));
            leagueName1= ((TextView) view.findViewById(R.id.textview_leftcircle_item_leaguename));
            leagueMemberCount1= ((TextView) view.findViewById(R.id.textview_leftcircle_item_membercount));
            leagueTopicCount1= ((TextView) view.findViewById(R.id.textview_leftcircle_item_topicscount));
            leagueThumb1= ((ImageView) view.findViewById(R.id.imageview_leftcircle_leftthumb));

            leagueName2= ((TextView) view.findViewById(R.id.textview_leftcircle_item_leaguename2));
            leagueMemberCount2= ((TextView) view.findViewById(R.id.textview_leftcircle_item_membercount2));
            leagueTopicCount2= ((TextView) view.findViewById(R.id.textview_leftcircle_item_topicscount2));
            leagueThumb2= ((ImageView) view.findViewById(R.id.imageview_leftcircle_leftthumb2));
            leagueMembersIcon2= ((ImageView) view.findViewById(R.id.imageview_leftcircle_memmbercount2));
            leagueTopicsIcon2= ((ImageView) view.findViewById(R.id.imageview_leftcircle_topics2));
            leftLayout= ((LinearLayout) view.findViewById(R.id.layout_left));
            rightLayout=((LinearLayout) ((LinearLayout) view.findViewById(R.id.layout_right)));
            relativeLayout= ((RelativeLayout) view.findViewById(R.id.relativelayout_right));
        }
    }
}
