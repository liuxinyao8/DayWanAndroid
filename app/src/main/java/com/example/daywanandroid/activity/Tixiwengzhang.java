package com.example.daywanandroid.activity;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.TixiwengzhangVP;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.fragment.TxwengzhangFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Tixiwengzhang extends BaseActivity {
    @BindView(R.id.TXwengzhang_tool)
    Toolbar TXwengzhangTool;
    @BindView(R.id.TxWengzhang_tab)
    TabLayout TxWengzhangTab;
    @BindView(R.id.txvp)
    ViewPager txvp;

    private ArrayList<Fragment> fragments;
    private ArrayList<ZstxBean.DataBean> zstxBeans;
    private List<ZstxBean.DataBean.ChildrenBean> children;
    private TxwengzhangFragment txwengzhangFragment;


    @Override
    protected void initData() {

    }

    @Override
    protected IPrestenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        zstxBeans = (ArrayList<ZstxBean.DataBean>) getIntent().getSerializableExtra("data");
        TXwengzhangTool.setTitle(zstxBeans.get(0).getName());
        setSupportActionBar(TXwengzhangTool);

        fragments = new ArrayList<>();

        if (zstxBeans != null) {
            children = zstxBeans.get(1).getChildren();
            for (ZstxBean.DataBean.ChildrenBean childrenBean : children) {
                TxWengzhangTab.addTab(TxWengzhangTab.newTab().setText(childrenBean.getName()));
                int id = childrenBean.getId();
                txwengzhangFragment = new TxwengzhangFragment();
                fragments.add(txwengzhangFragment);
                txwengzhangFragment.setId(id);
            }

            TixiwengzhangVP tixiwengzhangVP = new TixiwengzhangVP(getSupportFragmentManager(), fragments, children);
            txvp.setAdapter(tixiwengzhangVP);
        TxWengzhangTab.setupWithViewPager(txvp);



    }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tixiwengzhang;
    }

}
