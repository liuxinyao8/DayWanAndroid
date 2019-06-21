package com.example.daywanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.daywanandroid.R;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SYBannerWV extends BaseActivity {


    @BindView(R.id.bann_wv1)
    WebView bannWv1;

    @Override
    protected void initData() {

    }

    @Override
    protected IPrestenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        bannWv1.loadUrl(url);
        bannWv1.setWebViewClient(new WebViewClient());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sybanner_wv;
    }


}
