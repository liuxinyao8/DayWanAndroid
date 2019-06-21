package com.example.daywanandroid.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.SmartWengZhangAdapter;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.smartContract.SmartWengZhangContract;
import com.example.daywanandroid.model.bean.smartbean.SmartWengZhangBean;
import com.example.daywanandroid.presenter.smartpresenter.SamartWengZhangPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmartFragmnet extends BaseActivity implements SmartWengZhangContract.View {
    @BindView(R.id.tb_Smart)
    Toolbar tbSmart;
    @BindView(R.id.rv_SmWZ)
    RecyclerView rvSmWZ;
    private int page;
    private String k;
    private ArrayList<SmartWengZhangBean.DataBean.DatasBean> datasBeans;
    private SmartWengZhangAdapter smartWengZhangAdapter;

    @Override
    protected void initData() {
        ((SamartWengZhangPresenter) presenter).WengZhangS(k, page);
    }

    @Override
    protected IPrestenter createPresenter() {
        return new SamartWengZhangPresenter();
    }

    @Override
    protected void initView() {
        k = getIntent().getStringExtra("k");
        tbSmart.setTitle(k);
        setSupportActionBar(tbSmart);
        rvSmWZ.setLayoutManager(new LinearLayoutManager(this));
        datasBeans = new ArrayList<>();
        smartWengZhangAdapter = new SmartWengZhangAdapter(datasBeans, this);
        rvSmWZ.setAdapter(smartWengZhangAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_smart_fragmnet;
    }

    @Override
    public void swngzhang(SmartWengZhangBean smartWengZhangBean) {
        List<SmartWengZhangBean.DataBean.DatasBean> datas = smartWengZhangBean.getData().getDatas();
        datasBeans.addAll(datas);
       smartWengZhangAdapter.notifyDataSetChanged();
    }


}
