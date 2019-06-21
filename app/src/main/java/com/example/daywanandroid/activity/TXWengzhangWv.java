package com.example.daywanandroid.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.daywanandroid.R;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TXWengzhangWv extends BaseActivity {


    @BindView(R.id.Tx_WZ_tabl)
    Toolbar TxWZTabl;
    @BindView(R.id.Tx_wv)
    WebView TxWv;

    @Override
    protected void initData() {

    }

    @Override
    protected IPrestenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        String tittle = getIntent().getStringExtra("tittle");
        String link = getIntent().getStringExtra("link");
        TxWZTabl.setTitle(tittle);
        TxWv.loadUrl(link);
        TxWv.setWebViewClient(new WebViewClient());

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_txwengzhang_wv;
    }

}
