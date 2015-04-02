package com.ds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ds.dongqiudi.R;
import com.ds.entity.Events;

import java.util.List;

/**
 * Created by aaa on 15-4-2.
 */
public class Game_EventAdapter extends BaseAdapter{
    private Context context;
    private List<Events> list;

    public Game_EventAdapter(Context context, List<Events> list) {
        this.context = context;
        this.list = list;
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
            view = LayoutInflater.from(context).inflate(R.layout.event_item,viewGroup,false);
            holder.textView1 = ((TextView) view.findViewById(R.id.text1));
            holder.textView2 = ((TextView) view.findViewById(R.id.text2));
            view.setTag(holder);
        }else {
            holder = ((ViewHolder) view.getTag());
        }
            holder.textView1.setText(list.get(i).getMinute());
            holder.textView2.setText(list.get(i).getlist().get(0).getPerson());
        return view;
    }
    class ViewHolder{
        private TextView textView1;
        private TextView textView2;
    }
}
