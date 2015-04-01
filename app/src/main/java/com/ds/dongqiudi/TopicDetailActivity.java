package com.ds.dongqiudi;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ds.adapter.CommentAdapter;
import com.ds.entity.Attachment;
import com.ds.entity.Comment;
import com.ds.entity.League;
import com.ds.entity.Member;
import com.ds.entity.TopicDetailHeader;
import com.ds.utils.CircleURL;
import com.ds.utils.JsonToList;
import com.ds.utils.MyBitmapCache;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class TopicDetailActivity extends ActionBarActivity implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {
    private String id;
    private ImageView imageView_CircleThumb;
    private TextView textview_CircleTitle;
    private TextView textview_CircleDescrible;
    private PullToRefreshListView listview;
    private EditText edittext_EditComment;
    private ImageView imageView_avatar;
    private TextView textview_MemberName;
    private TextView textview_CreatedAt;
    private TextView textview_Title;
    private TextView textview_Content;
    private TextView textview_AgreeDetail;
    private RequestQueue mQueue;
    private StringRequest stringRequest;
    private View view;
    private ImageView[]imageView_Images=new ImageView[6];
    private LinearLayout container_Images;
    private TextView textview_CommentCount;
    private ImageLoader mLoader;
    private List<Comment>totalList;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail_main);
        id=getIntent().getExtras().getString("id");

        //初始化控件
        initView();
        //获得头部数据并给listview设置头部
        getHeadData();
        //获得评论数据
        getCommentData();
        adapter=new CommentAdapter(totalList,this,mQueue);
        listview.setAdapter(adapter);
        listview.setOnRefreshListener(this);
        listview.setOnItemClickListener(this);



    }

    //初始化控件
    private void initView() {
        mQueue= Volley.newRequestQueue(this);
        mLoader=new ImageLoader(mQueue, MyBitmapCache.getInstance());
        totalList=new ArrayList<>();
        imageView_CircleThumb = ((ImageView) findViewById(R.id.imageView_rightdetailcirclethumb));
        textview_CircleTitle = ((TextView) findViewById(R.id.textView_rightdetailtitle));
        textview_CircleDescrible = ((TextView) findViewById(R.id.textView_rightcircledescrible));
        edittext_EditComment = ((EditText) findViewById(R.id.edittext_rightdetailedit));

        listview = ((PullToRefreshListView) findViewById(R.id.listview_rightdetail_content));
        //头部
        view= LayoutInflater.from(this).inflate(R.layout.rightdetail_head,null);
        imageView_avatar = ((ImageView) view.findViewById(R.id.imageview_topicdetail_avatar));
        textview_MemberName = ((TextView) view.findViewById(R.id.textView_topicdetail_membername));
        textview_CreatedAt = ((TextView) view.findViewById(R.id.textView_topicdetail_createdat));
        textview_Title = ((TextView) view.findViewById(R.id.textview_topicdetail_title));
        textview_Content = ((TextView) view.findViewById(R.id.textView_topicdetail_content));
        container_Images = ((LinearLayout) view.findViewById(R.id.contain_iamges));
        for (int i = 0; i < imageView_Images.length; i++) {
            imageView_Images[i]= (ImageView) container_Images.getChildAt(i);
        }
        textview_AgreeDetail = ((TextView) view.findViewById(R.id.textview_topicdetail_agreedetail));
        textview_CommentCount = ((TextView) view.findViewById(R.id.textview_topicdetail_commentcount));

    }

    //获取头部数据并给ListView设置头部
    private void getHeadData() {
        stringRequest=new StringRequest(CircleURL.toppicContent+id,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                TopicDetailHeader header= JsonToList.jsonToTopicDetailHeader(s);

                if (header != null) {

                    textview_Title.setText(header.getTitle());
                    textview_Content.setText(header.getContent());
                    textview_CreatedAt.setText(header.getCreated_at());
                    textview_CommentCount.setText("总评论数("+header.getTotal_replies()+")");

                    League group = header.getGroup();
                    if (group!=null&&!"null".equals(group.getThumb())){
                       textview_CircleTitle.setText(group.getTitle());
                        textview_CircleDescrible.setText(group.getDescrible());
                        mLoader.get(group.getThumb(),mLoader.getImageListener(imageView_CircleThumb,
                                R.drawable.product_fail, R.drawable.defaultcovers),0,0);
                    }

                    Member author = header.getAuthor();
                    if(author!=null&&!"null".equals(author.getAvatar())){
                        textview_MemberName.setText(author.getUsername());
                        mLoader.get(author.getAvatar(),mLoader.getImageListener(imageView_avatar,
                                R.drawable.product_fail, R.drawable.defaultcovers),120,120);
                    }

                    List<Member> last_up_users = header.getLast_up_users();
                    StringBuilder str=new StringBuilder();
                    if (last_up_users.size()>0){
                        for (int i = 0; i < last_up_users.size(); i++) {
                            str.append(last_up_users.get(i).getUsername() + ",");
                        }
                        str.append("..."+last_up_users.size()+"人赞过");
                    }else{
                        str.append("还没有人赞过哦！");
                    }
                    textview_AgreeDetail.setText(str);

                    List<Attachment> attachments = header.getAttachments();
                    if(attachments!=null&&attachments.size()>0){
                        for (int i = 0; i < attachments.size(); i++) {
                            Attachment attachment = attachments.get(i);
                            if(attachment!=null&&!"null".equals(attachment.getUrl())){
                                imageView_Images[i].setVisibility(View.VISIBLE);
                                mLoader.get(attachment.getUrl(),mLoader.getImageListener(
                                        imageView_Images[i],R.drawable.product_fail,
                                        R.drawable.defaultcovers),0,0);
                            }
                        }
                    }
                    listview.getRefreshableView().addHeaderView(view);
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(TopicDetailActivity.this,"网络访问失败！！！！！",Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(stringRequest);
    }

    //获得评论数据
    public void getCommentData(){
        stringRequest=new StringRequest(CircleURL.topicComment+id+"?sort=up",
         new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                List<Comment>list=JsonToList.commentJsonToList(s);
                if (list != null) {
                    totalList.addAll(list);
                    //提醒listview更新
                    adapter.notifyDataSetChanged();
                    listview.onRefreshComplete();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(TopicDetailActivity.this,"网络访问失败！！！！！",Toast.LENGTH_SHORT).show();
            }
        });
      mQueue.add(stringRequest);
    }

    public void clickButton(View view){
        switch (view.getId()){
            //被点击以后当前页面销毁
            case R.id.imageView_rightdetailback:
                finish();
                break;
            case R.id.imageView_rightcircletocircle:
                //被点击后进入相应圈子的详情
                break;
            //点击后收藏
            case R.id.imageview_rightcirclecollect:
                break;
            //点击后分享
            case R.id.imageview_rightdetailshare:
                showShare();
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        totalList.clear();
        getCommentData();

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
