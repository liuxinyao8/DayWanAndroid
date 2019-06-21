package com.example.daywanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.daywanandroid.R;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxWv extends BaseActivity {


    @BindView(R.id.tb_WX)
    Toolbar tbWX;
    @BindView(R.id.wv_Wx)
    WebView wvWx;

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
        String linkk = intent.getStringExtra("linkk");
        String titlse = intent.getStringExtra("titlse");
        tbWX.setTitle(titlse);
        setSupportActionBar(tbWX);
        wvWx.loadUrl(linkk);
        wvWx.setWebViewClient(new WebViewClient());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wx_wv;
    }


}
