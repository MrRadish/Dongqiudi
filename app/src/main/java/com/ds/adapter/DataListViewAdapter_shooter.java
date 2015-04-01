package com.ds.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.dongqiudi.MemberActivity;
import com.ds.dongqiudi.R;
import com.ds.dongqiudi.TeamActivity;
import com.ds.entity.DataIntegral;
import com.ds.entity.DataShooter;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by aaa on 15-3-27.
 */
public class DataListViewAdapter_shooter extends BaseAdapter implements View.OnClickListener{
   private List list_shooter;
    private Context context;
    private BitmapUtils bitmapUtils;
    private Intent intent;


    public DataListViewAdapter_shooter(List list_integral, Context context) {
        this.list_shooter = list_integral;
        this.context = context;



    }
    @Override
    public int getCount() {
        return list_shooter.size();
    }

    @Override
    public Object getItem(int i) {
        return list_shooter.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        bitmapUtils=new BitmapUtils(context);
        ViewHolder viewHolder=null;
        if (view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.datalistviewshooter_item,viewGroup,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }

        DataShooter shooter= (DataShooter) list_shooter.get(i);
        if(i<3)
        {
            view.setBackgroundResource(R.drawable.item_bg);
        }
        viewHolder.text_name.setText(shooter.getName());
        viewHolder.text_name.setOnClickListener(this);
        viewHolder.text_team.setText(shooter.getTeam());
        viewHolder.text_team.setOnClickListener(this);
        viewHolder.text_num.setText(shooter.getProcessNum()+"");
        String imgurl="http://img.dongqiudi.com/data/pic/"+shooter.getTeam_id()+".png";
        bitmapUtils.display(viewHolder.img_Team,imgurl);
        viewHolder.img_Team.setOnClickListener(this);
        return view;
    }

    @Override
    //点击事件
    public void onClick(View view) {
    switch (view.getId())
    {
        case R.id.item_img_shooter:
        case R.id.item_text_team_shooter:
           intent=new Intent(view.getContext(),TeamActivity.class);
            view.getContext().startActivity(intent);
        break;
        case R.id.item_text_name_shooter:
           intent=new Intent(view.getContext(),MemberActivity.class);
            view.getContext().startActivity(intent);
            break;
    }

    }

    class ViewHolder{

        private TextView text_name;
        private TextView text_team;
        private TextView text_num;
        private ImageView img_Team;


        ViewHolder(View view) {
            this.img_Team= (ImageView) view.findViewById(R.id.item_img_shooter);
           this.text_name= (TextView) view.findViewById(R.id.item_text_name_shooter);
           this.text_team= (TextView) view.findViewById(R.id.item_text_team_shooter);
           this.text_num= (TextView) view.findViewById(R.id.item_text_num_shooter);
        }
    }
    public void add(List<DataShooter> data){
        list_shooter.addAll(data);
        notifyDataSetChanged();
    }
}
