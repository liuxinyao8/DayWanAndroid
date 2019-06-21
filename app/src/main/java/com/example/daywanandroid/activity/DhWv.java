package com.example.daywanandroid.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.daywanandroid.R;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DhWv extends BaseActivity {


    @BindView(R.id.wv_sh)
    WebView wvSh;

    @Override
    protected void initData() {

    }

    @Override
    protected IPrestenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra("url");
         wvSh.loadUrl(url);
         wvSh.setWebViewClient(new WebViewClient());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dh_wv;
    }


}
