package com.ds.dongqiudi;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ArticleViewActivity extends ActionBarActivity implements View.OnClickListener {

    private WebView webView;
    private String shareUrl;
    private TextView title;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        Bundle bundle=getIntent().getExtras().getBundle("url");
        shareUrl = bundle.getString("articleurl");
        Toast.makeText(this,"ArticleActivity"+ shareUrl,Toast.LENGTH_SHORT).show();
        intiUI();
    }
    public void intiUI(){
        webView = ((WebView) findViewById(R.id.article_webview));
        title = ((TextView) findViewById(R.id.article_txt_title));
        btnBack = ((ImageView) findViewById(R.id.back_btn));
        btnBack.setOnClickListener(this);
        webView.setWebViewClient(new WebViewClient());
        if(shareUrl!=null&&!shareUrl.equals("")){
            webView.loadUrl(shareUrl);
            //手势控制缩放
            webView.getSettings().setBuiltInZoomControls(true);
            //屏幕自适应
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
