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

public class SamrtActivityWv extends BaseActivity {


    @BindView(R.id.tb_Ssmart)
    Toolbar tbSsmart;
    @BindView(R.id.wv_Smart)
    WebView wvSmart;

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
        String top = intent.getStringExtra("top");
        tbSsmart.setTitle(top);
        setSupportActionBar(tbSsmart);
        String l = intent.getStringExtra("l");
        wvSmart.loadUrl(l);
        wvSmart.setWebViewClient(new WebViewClient());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_samrt_wv;
    }


}
