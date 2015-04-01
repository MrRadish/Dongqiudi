package com.ds.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.ds.dongqiudi.R;
import com.ds.entity.Attachment;
import com.ds.entity.HotTopic;
import com.ds.entity.Member;
import com.ds.utils.MyBitmapCache;

import java.util.List;

/**
 * Created by aaa on 15-3-26.
 */
public class HotTopicAdapter extends BaseAdapter{
    private List<HotTopic> list;
    private Context context;
    private ImageLoader mLoader;

    public HotTopicAdapter(List<HotTopic> list, Context context,RequestQueue mQueue) {
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
            view= LayoutInflater.from(context).inflate(R.layout.fragment_rightcircle_item,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder mHolder= (ViewHolder) view.getTag();
        HotTopic hotTopic = list.get(i);
        if (hotTopic != null) {
            Member author = hotTopic.getAuthor();
            mHolder.memberName.setText(author.getUsername());
            mHolder.createdAt.setText(hotTopic.getCreated_at().substring(0,16));
            mHolder.leagueName.setText(hotTopic.getGroup().getTitle());
            mHolder.title.setText(hotTopic.getTitle());
            mHolder.content.setText(hotTopic.getContent());
            mHolder.commentCount.setText(""+hotTopic.getTotal_replies());

            String avatarString = author.getAvatar();
            if ("null".equals(avatarString)) {
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mHolder.avatar.getLayoutParams(); // 取控件mGrid当前的布局参数
                linearParams.height = 120;                    // 当控件的高强制设成50象素
                linearParams.width=120;
                mHolder.avatar.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件上

                mHolder.avatar.setImageResource(R.drawable.defaultcovers);
            }else{
                mLoader.get(avatarString, mLoader.getImageListener(mHolder.avatar,
                        R.drawable.product_fail, R.drawable.defaultcovers), 120, 120);
                if(hotTopic.getAttachments_total()>0) {
                    List<Attachment> attachments = hotTopic.getAttachments();
                    for (int j = 0; j < attachments.size(); j++) {
                        String thumb = attachments.get(j).getThumb();

                        if(j==0) {
                            mHolder.image1.setVisibility(View.VISIBLE);
                            mLoader.get(thumb, mLoader.getImageListener(mHolder.image1,
                                    R.drawable.product_fail, R.drawable.defaultcovers), 280, 300);
                        }else if(j==1){
                            mHolder.image2.setVisibility(View.VISIBLE);
                            mLoader.get(thumb, mLoader.getImageListener(mHolder.image2,
                                    R.drawable.product_fail, R.drawable.defaultcovers), 280, 300);
                        }else if(j==2){
                            mLoader.get(thumb, mLoader.getImageListener(mHolder.image3,
                                    R.drawable.product_fail, R.drawable.defaultcovers), 280, 300);
                            mHolder.image3.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

        }

        return view;
    }

    public static class ViewHolder{

        private ImageView avatar;
        private TextView memberName;
        private TextView createdAt;
        private TextView leagueName;
        private TextView title;
        private TextView content;
        private ImageView image1;
        private ImageView image2;
        private ImageView image3;
        private TextView  commentCount;

        public ViewHolder(View convertview){
            avatar=((ImageView) convertview.findViewById(R.id.avatar));
            memberName= ((TextView) convertview.findViewById(R.id.textView_membername));
            createdAt= ((TextView) convertview.findViewById(R.id.textView_createdtime));
            leagueName= ((TextView) convertview.findViewById(R.id.textView_leaguename));
            content= ((TextView) convertview.findViewById(R.id.textview_topiccontent));
            image1= ((ImageView) convertview.findViewById(R.id.image1));
            image2= ((ImageView) convertview.findViewById(R.id.image2));
            image3= ((ImageView) convertview.findViewById(R.id.image3));
            commentCount= ((TextView) convertview.findViewById(R.id.textView_commentcount));
            title= ((TextView) convertview.findViewById(R.id.textview_topictitle));
        }
    }
}
