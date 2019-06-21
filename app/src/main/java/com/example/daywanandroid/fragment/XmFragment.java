package com.example.daywanandroid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.XMwengzhangVP;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.xmcontract.XMContract;
import com.example.daywanandroid.model.bean.xmbean.XmBean;
import com.example.daywanandroid.presenter.xmpresenter.XmPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class XmFragment extends BaseFragment implements XMContract.View {


    @BindView(R.id.xm_tab)
    TabLayout xmTab;
    @BindView(R.id.XM_vp)
    ViewPager XMVp;
    private ArrayList<Fragment> fragments;
    private ArrayList<XmBean.DataBean> dataBeans;
    private XmwengzhangFragmnet xmwengzhangFragmnet;
    private XMwengzhangVP xMwengzhangVP;


    public XmFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initData() {
        ((XmPresenter)presenter).getprojectp();
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new XmPresenter();
    }

    @Override
    public void getproject(XmBean xmBean) {
        List<XmBean.DataBean> data = xmBean.getData();
        dataBeans.addAll(data);
        for (int i = 0; i < data.size() ; i++) {
            XmwengzhangFragmnet xmwengzhangFragmnet = new XmwengzhangFragmnet();
            int id = data.get(i).getId();
            xmwengzhangFragmnet.setId(id);
            fragments.add(xmwengzhangFragmnet);
        }

        xMwengzhangVP.notifyDataSetChanged();



    }
    @Override
    protected void initView() {
        dataBeans = new ArrayList<>();
        fragments = new ArrayList<>();
        xMwengzhangVP = new XMwengzhangVP(getChildFragmentManager(), fragments, dataBeans);
        XMVp.setAdapter(xMwengzhangVP);
        xmTab.setupWithViewPager(XMVp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xm;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Log.e("项目的文章：",err);
    }


}
