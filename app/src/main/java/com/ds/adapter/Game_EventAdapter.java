package com.ds.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ds.dongqiudi.R;
import com.ds.entity.Events;
import com.ds.utils.MyIncidentSelector;
import com.ds.utils.PxToDp;

import java.util.List;

/**
 * Created by aaa on 15-4-2.
 */
public class Game_EventAdapter extends BaseAdapter{
    private Context context;
    private List<Events> list;
    private String a_id;
    private RelativeLayout.LayoutParams defultlinearLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.MATCH_PARENT);

    public Game_EventAdapter(Context context, List<Events> list,String a) {
        this.context = context;
        this.list = list;
        this.a_id = a;
    }

    @Override
    public int getCount() {
        if (list.size()!=0) {
            return list.size() + 2;
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return i;
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
            view = LayoutInflater.from(context).inflate(R.layout.fragment_game_detail_incident_item,viewGroup,false);
            holder.event_relativeLayout = ((RelativeLayout) view.findViewById(R.id.event_relativeLayout));
            holder.event_time = ((TextView) view.findViewById(R.id.event_time));
            holder.event_link_top = ((ImageView) view.findViewById(R.id.event_link_top));
            holder.event_LinearLayout = ((LinearLayout) view.findViewById(R.id.event_linearLayout));
            holder.event_link_bottom = ((ImageView) view.findViewById(R.id.event_link_bottom));
            holder.event_MyContextLeft = ((LinearLayout) view.findViewById(R.id.event_MyContext_left));
            holder.event_MyContextRight = ((LinearLayout) view.findViewById(R.id.event_MyContext_right));
            if (i!=0&&i!=list.size()+1){
                TextView textView = null;
                ImageView imageView = null;
                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(PxToDp.Dp2Px(context,20),PxToDp.Dp2Px(context,20));
                if (a_id.equals(list.get(i - 1).getTeam_id())) {
//                    holder.event_MyContextRight.setVisibility(View.GONE);
//                    holder.event_MyContextLeft.setVisibility(View.VISIBLE);
                    Log.i("Info",list.get(i-1).getlist().size()+"左边的数量");
                    for (int a = 0; a < list.get(i - 1).getlist().size(); a++) {
                        imageView = new ImageView(context);
                        imageView.setLayoutParams(imageParams);
//                    imageView.setImageResource(MyIncidentSelector.getNewsImage(list.get(i - 1).getlist().get(a).getCode()));
                        textView = new TextView(context);
                        textView.setLayoutParams(textParams);
//                    textView.setText(list.get(i - 1).getlist().get(a).getPerson());
                        holder.event_MyContextLeft.addView(textView,a);
//                        holder.event_MyContextLeft.addView(imageView);
                    }
                }else {
                    Log.i("Info",list.get(i-1).getlist().size()+"左边的数量");
                    for (int a = 0; a < list.get(i - 1).getlist().size(); a++) {
                        imageView = new ImageView(context);
                        imageView.setLayoutParams(imageParams);
//                        imageView.setImageResource(MyIncidentSelector.getNewsImage(list.get(i - 1).getlist().get(a).getCode()));
                        textView = new TextView(context);
                        textView.setLayoutParams(textParams);
//                        textView.setText(list.get(i - 1).getlist().get(a).getPerson());
                        holder.event_MyContextRight.addView(textView,a);
//                        holder.event_MyContextRight.addView(imageView);
                    }
                }
            }
            view.setTag(holder);
        }else {
            holder = ((ViewHolder) view.getTag());
        }
            if (i == 0){
                holder.event_MyContextRight.setVisibility(View.GONE);
                holder.event_MyContextLeft.setVisibility(View.GONE);
                holder.event_link_top.setVisibility(View.GONE);
                holder.event_time.setText("0");
                holder.event_link_bottom.setVisibility(View.GONE);
            }else if (i == list.size()+1){
                holder.event_MyContextRight.setVisibility(View.GONE);
                holder.event_MyContextLeft.setVisibility(View.GONE);
                holder.event_link_bottom.setVisibility(View.GONE);
                holder.event_time.setText("完");
                holder.event_link_top.setVisibility(View.GONE);
            }else{
                holder.event_link_bottom.setVisibility(View.VISIBLE);
                holder.event_link_top.setVisibility(View.VISIBLE);
                holder.event_time.setText(list.get(i-1).getMinute());

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(PxToDp.Dp2Px(context,20),PxToDp.Dp2Px(context,20));
                        TextView textView = null;
                        ImageView imageView = null;
                    if (a_id.equals(list.get(i - 1).getTeam_id())){
                        holder.event_MyContextRight.setVisibility(View.VISIBLE);
                        holder.event_MyContextLeft.setVisibility(View.VISIBLE);
                        int count = holder.event_MyContextLeft.getChildCount();
                        for (int a = 0;a<count-1;a++) {
//                            if (a%2==0){
                                ((TextView) holder.event_MyContextLeft.getChildAt(a)).setText(list.get(i -1).getlist().get(a).getPerson());
//                            }else if (a%2!=0){
//                                ((ImageView) holder.event_MyContextLeft.getChildAt(a)).setImageResource(R.drawable.ic_drawer);
//                            }
//                            imageView = new ImageView(context);
//                            imageView.setLayoutParams(imageParams);
//                            imageView.setImageResource(MyIncidentSelector.getNewsImage(list.get(i - 1).getlist().get(a).getCode()));
//                            textView = new TextView(context);
//                            textView.setLayoutParams(textParams);
//                            textView.setText(list.get(i - 1).getlist().get(a).getPerson());
                        }
                        int left = holder.event_MyContextLeft.getHeight();
                        int axle = holder.event_LinearLayout.getHeight();
//                        if (left > axle) {
//                            RelativeLayout.LayoutParams Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                                    left+50);
//                            Layout.addRule(RelativeLayout.CENTER_IN_PARENT);
//                            holder.event_LinearLayout.setLayoutParams(Layout);
//                        }else {
//                            holder.event_LinearLayout.setLayoutParams(defultlinearLayout);
//                        }
                    }else {
                        holder.event_MyContextLeft.setVisibility(View.VISIBLE);
                        holder.event_MyContextRight.setVisibility(View.VISIBLE);
                        int count = holder.event_MyContextRight.getChildCount();
                        for (int a = 0;a<count;a++) {
//                            if (a % 2 == 0) {
                                ((TextView) holder.event_MyContextRight.getChildAt(a)).setText(list.get(i - 1).getlist().get(a).getPerson());
//                            } else {
//                                ((ImageView) holder.event_MyContextRight.getChildAt(a)).setImageResource(MyIncidentSelector.getNewsImage(list.get(i - 1).getlist().get(a - (a / 2)).getCode()));
//                            }
                        }
                        //判断时光轴的高度是否被文字框超越，如是，则增加时光轴高度
//                            int right = holder.event_MyContextRight.getHeight();
//                            int axle = holder.event_LinearLayout.getHeight();
//                            if (right > axle){
//                                RelativeLayout.LayoutParams Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                                       right+50);
//                                Layout.addRule(RelativeLayout.CENTER_IN_PARENT);
//                                holder.event_LinearLayout.setLayoutParams(Layout);
//                            }else {
//                                holder.event_LinearLayout.setLayoutParams(defultlinearLayout);
//                            }
                    }
            }
        return view;
    }
    class ViewHolder{
        private LinearLayout event_MyContextLeft;
        private LinearLayout event_MyContextRight;
        private LinearLayout event_LinearLayout;
        private RelativeLayout event_relativeLayout;
        private TextView event_time;
        private ImageView event_link_top;
        private ImageView event_link_bottom;
    }
}
