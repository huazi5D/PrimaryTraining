package com.li.primary.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.BaseActivity;

/**
 * Created by liu on 2017/6/9.
 */

public class NewDetailsActivity extends BaseActivity implements View.OnClickListener{
    private WebView mWebView;
    private TextView mTvTitle;
    private ImageView mIvBack;
    private String url;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        title = bundle.getString("title");
        if(TextUtils.isEmpty(url)){
            finish();
        }
        initView();
    }

    private void initView(){
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
