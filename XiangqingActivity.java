package com.example.before_170503_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class XiangqingActivity extends AppCompatActivity {

    @Bind(R.id.xiangqing_web)
    WebView xiangqingWeb;
    @Bind(R.id.activity_xiangqing)
    RelativeLayout activityXiangqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        xiangqingWeb.loadUrl(url);
        xiangqingWeb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}
