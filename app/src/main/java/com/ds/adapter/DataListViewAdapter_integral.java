package com.ds.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.dongqiudi.MainActivity;
import com.ds.dongqiudi.R;
import com.ds.dongqiudi.TeamActivity;
import com.ds.entity.DataIntegral;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by aaa on 15-3-27.
 */
public class DataListViewAdapter_integral extends BaseAdapter implements View.OnClickListener {
   private List list_integral;
    private Context context;
    private BitmapUtils bitmapUtils;
    public DataListViewAdapter_integral(List list_integral, Context context) {
        this.list_integral = list_integral;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list_integral.size();
    }

    @Override
    public Object getItem(int i) {
        return list_integral.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        bitmapUtils =new BitmapUtils(context);
        ViewHolder viewHolder=null;
        if (view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.datalistviewintegarl_item,viewGroup,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }

        DataIntegral  integral= (DataIntegral) list_integral.get(i);

        viewHolder.text_team.setText(integral.getTeam());
        viewHolder.text_team.setOnClickListener(this);

        viewHolder.text_order.setText(integral.getMatcheTotal()+"");
        viewHolder.text_victory.setText(integral.getVictory()+"");
        viewHolder.text_draw.setText(integral.getDraw()+"");
        viewHolder.text_fail.setText(integral.getLost()+"");
        viewHolder.text_balance.setText(integral.getProcess()+"/"+integral.getLose());
        viewHolder.text_integral.setText(integral.getPoint()+"");

        viewHolder.text_rank.setText(integral.getRanking());
        if(Integer.parseInt(integral.getRanking())<4)
        {
            view.setBackgroundResource(R.drawable.item_bg);
        }
        String imgUrl="http://img.dongqiudi.com/data/pic/"+integral.getImg_id()+".png";
        bitmapUtils.display(viewHolder.image_team,imgUrl);
        viewHolder.image_team.setOnClickListener(this);

        return view;
    }
    //球队的可点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.item_img_integral:
            case R.id.item_text_name:
                Intent intent=new Intent(view.getContext(),TeamActivity.class);
                view.getContext().startActivity(intent);
                break;
        }

    }

    class ViewHolder{
        private TextView text_rank;
        private ImageView image_team;
        private TextView text_team;
        private TextView text_order;
        private TextView text_victory;
        private TextView text_fail;
        private TextView text_draw;
        private TextView text_balance;
        private TextView text_integral;

        ViewHolder(View view) {
            this.image_team= (ImageView) view.findViewById(R.id.item_img_integral);

            this.text_rank= (TextView) view.findViewById(R.id.item_text_ranking);
           this.text_team= (TextView) view.findViewById(R.id.item_text_name);
           this.text_order= (TextView) view.findViewById(R.id.item_text_mache);
           this.text_victory= (TextView) view.findViewById(R.id.item_text_victory);
           this.text_fail= (TextView) view.findViewById(R.id.item_text_fail);
           this.text_draw= (TextView) view.findViewById(R.id.item_text_draw);
           this.text_balance= (TextView) view.findViewById(R.id.item_text_balance);
           this.text_integral= (TextView) view.findViewById(R.id.item_text_integral);
        }
    }
    public void add(List<DataIntegral> dtat){
        list_integral.addAll(dtat);
        notifyDataSetChanged();
    }
}
