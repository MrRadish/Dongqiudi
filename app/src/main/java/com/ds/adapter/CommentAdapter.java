package com.ds.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.ds.dongqiudi.R;
import com.ds.entity.Comment;
import com.ds.entity.Member;
import com.ds.utils.MyBitmapCache;

import java.util.List;

/**
 * Created by aaa on 15-3-31.
 */
public class CommentAdapter extends BaseAdapter{
    private List<Comment> list;
    private Context context;
    private ImageLoader mLoader;

    public CommentAdapter(List<Comment> list, Context context,RequestQueue mQueue) {
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
            view= LayoutInflater.from(context).inflate(R.layout.fragment_rightcircle_topicdetail_comment,
                    viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder mHolder= (ViewHolder) view.getTag();
        Comment comment = list.get(i);
        if (comment != null) {
            mHolder.cotent.setText(comment.getContent());
            mHolder.floor.setText(comment.getFloor()+"楼");
            mHolder.created_at.setText(comment.getCreated_at().substring(0,16));
            Member author = comment.getAuthor();
            if (author != null&&!"null".equals(author.getAvatar())) {
                mHolder.username.setText(author.getUsername());
                mLoader.get(author.getAvatar(),mLoader.getImageListener(mHolder.avatar,
                        R.drawable.product_fail, R.drawable.defaultcovers  ),120,120);
            }
        }
        return view;
    }

    public static class ViewHolder{
        private ImageView avatar;
        private TextView username;
        private TextView floor;
        private TextView created_at;
        private TextView cotent;
        public ViewHolder(View view){
            avatar= ((ImageView) view.findViewById(R.id.image_comment_useravatar));
            username= ((TextView) view.findViewById(R.id.textView_comment_username));
            floor= ((TextView) view.findViewById(R.id.textView_comment_floor));
            created_at= ((TextView) view.findViewById(R.id.textView_comment_created_at));
            cotent= ((TextView) view.findViewById(R.id.textView_comment_content));
        }
    }
}
