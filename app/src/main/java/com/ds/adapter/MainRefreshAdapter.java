package com.ds.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.dongqiudi.R;
import com.ds.entity.MainArticle;
import com.ds.utils.BitmapHelper;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;


import java.util.List;

/**
 * 显示文章列表
 * Created by Administrator on 2015/3/25.
 */
public class MainRefreshAdapter extends BaseAdapter {

    private Context context;
    private List<MainArticle> data;
    private BitmapUtils bitmapUtils;
    public MainRefreshAdapter(Context context,List<MainArticle> data) {
        this.context=context;
        this.data=data;
        bitmapUtils=new BitmapUtils(context);
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
            view= LayoutInflater.from(context).inflate(R.layout.fragment_dum_a_item,viewGroup,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        MainArticle article=data.get(i);
        //显示图片内容
        BitmapHelper.getBitmapUtils().display(holder.itemImg,article.getImgUrl(),new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });
//        final ViewHolder finalHolder = holder;
        //显示文本内容
        holder.itemTtitle.setText(article.getTitle());
        //显示文章描述
        if(holder.itemDescription.equals("")||holder.itemDescription==null){
            holder.itemDescription.setVisibility(View.GONE);
        }else{
            holder.itemDescription.setText(article.getDescription());
        }
        holder.comments.setText(""+article.getComments());
        if(article.getLabel()!=null&&!article.getLabel().equals("")&&article.getLabelColor()!=null&&!article.equals("")){
            holder.label.setVisibility(View.VISIBLE);
            holder.label.setText(article.getLabel());
            String labelColor = article.getLabelColor();
//            Log.d("===","===Color"+labelColor);
            if(!"".equals(labelColor)){
                holder.label.setBackgroundColor(Color.parseColor(labelColor));
            }

        }else{
            holder.label.setVisibility(View.GONE);
        }
        return view;
    }
    //复用
    public class ViewHolder{
        private TextView comments;
        private TextView label;
        private ImageView itemImg;
        private TextView itemTtitle;
        private TextView itemDescription;
        public ViewHolder(View view){
            itemImg= ((ImageView) view.findViewById(R.id.item_img));
            itemTtitle= ((TextView) view.findViewById(R.id.item_txt_title));
            itemDescription= ((TextView) view.findViewById(R.id.item_txt_description));
            comments= ((TextView) view.findViewById(R.id.btm_txt_commenta));
            label= ((TextView) view.findViewById(R.id.btm_label));
        }
    }
    //更新数据
    public void addAll(List<MainArticle> list){
        data.addAll(list);
        notifyDataSetChanged();
    }
}
