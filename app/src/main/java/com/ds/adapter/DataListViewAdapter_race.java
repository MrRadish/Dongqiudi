package com.ds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.dongqiudi.R;
import com.ds.entity.DataIntegral;
import com.ds.entity.DataMatche;
import com.ds.entity.DataShooter;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by aaa on 15-3-27.
 */
public class DataListViewAdapter_race extends BaseAdapter {
   private List list_race;
    private Context context;
    private BitmapUtils bitmapUtils;


    public DataListViewAdapter_race(List list_integral, Context context) {
        this.list_race = list_integral;
        this.context = context;




    }
    @Override
    public int getCount() {
        return list_race.size();
    }

    @Override
    public Object getItem(int i) {
        return list_race.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        bitmapUtils=new BitmapUtils(context);
        if (view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.datalistviewrace_item,viewGroup,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }

        DataMatche matche= (DataMatche) list_race.get(i);
        if(i<3)
        {
            view.setBackgroundResource(R.drawable.item_bg);
        }
        String date=matche.getDate().substring(5);
        String time=matche.getTime().substring(0,5);
        viewHolder.text_time.setText(date+" "+time);
        viewHolder.text_ateam.setText(matche.getaName());
        viewHolder.text_score.setText(matche.getaScore()+":"+matche.getbScore());
        viewHolder.text_bname.setText(matche.getbName());
        String imageUrl_a="http://img.dongqiudi.com/data/pic/"+matche.getaImage()+".png";
        String imageUrl_b="http://img.dongqiudi.com/data/pic/"+matche.getBimage()+".png";
        bitmapUtils.display(viewHolder.img_a,imageUrl_a);
        bitmapUtils.display(viewHolder.img_b,imageUrl_b);



        return view;
    }
    class ViewHolder{

        private TextView text_time;
        private TextView text_ateam;
        private ImageView img_a;
        private TextView text_score;
        private  ImageView img_b;
        private TextView text_bname;

        ViewHolder(View view) {
           this.text_time= (TextView) view.findViewById(R.id.item_text_time_race);
           this.text_ateam= (TextView) view.findViewById(R.id.item_text_aname_race);
           this.img_a= (ImageView) view.findViewById(R.id.item_aimg_race);
            this.text_score= (TextView) view.findViewById(R.id.item_text_score_race);
            this.img_b= (ImageView) view.findViewById(R.id.item_bimg_race);
            this.text_bname= (TextView) view.findViewById(R.id.item_text_bname_race);

        }
    }
    public void add(List<DataMatche> data){
        list_race.addAll(data);
        notifyDataSetChanged();
    }
}
