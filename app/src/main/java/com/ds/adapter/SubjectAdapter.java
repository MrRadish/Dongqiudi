package com.ds.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.dongqiudi.R;
import com.ds.entity.SubjectArticle;
import com.ds.utils.BitmapHelper;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
public class SubjectAdapter extends BaseAdapter {
    private Context context;
    private List<SubjectArticle> data;

    public SubjectAdapter(Context context,List<SubjectArticle> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_main_subject_item, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        SubjectArticle article=data.get(i);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        BitmapHelper.getBitmapUtils().display(holder.sbjImag,article.getImgUrl(),new BitmapLoadCallBack<ImageView>() {
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
    public class ViewHolder{
        private ImageView sbjImag;
        private TextView title;
        private TextView description;
        public ViewHolder(View view){
            this.sbjImag= ((ImageView) view.findViewById(R.id.subject_img));
            this.title= ((TextView) view.findViewById(R.id.subject_title));
            this.description= ((TextView) view.findViewById(R.id.subject_description));
        }
    }
    public void addAll(List<SubjectArticle> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
