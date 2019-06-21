package com.example.daywanandroid.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.WxwengzhangVP;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.wxcontract.WxContract;
import com.example.daywanandroid.model.bean.wx.WXtabBean;
import com.example.daywanandroid.presenter.wxpresenter.WXtabPresenter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GzhFragment extends BaseFragment implements WxContract.View {


    @BindView(R.id.wx_tab)
    TabLayout wxTab;
    @BindView(R.id.wx_vp)
    ViewPager wxVp;
    Unbinder unbinder;
    private ArrayList<WXtabBean.DataBean> dataBeans;
    private ArrayList<Fragment> fragments;
    private WxwengzhangVP wxwengzhangVP;

    public GzhFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initData() {
        ((WXtabPresenter) presenter).Wxwngzhang();
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new WXtabPresenter();
    }

    @Override
    protected void initView() {
        dataBeans = new ArrayList<>();
        fragments = new ArrayList<>();
        wxwengzhangVP = new WxwengzhangVP(getChildFragmentManager(), fragments, dataBeans);
        wxVp.setAdapter(wxwengzhangVP);
        wxTab.setupWithViewPager(wxVp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gzh;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }


    @Override
    public void Wxtab(WXtabBean wXtabBean) {
        List<WXtabBean.DataBean> data = wXtabBean.getData();
        dataBeans.addAll(data);
        for (int i = 0; i <data.size() ; i++) {
            int id = data.get(i).getId();
            WxwengZhangFragment wxwengZhangFragment = new WxwengZhangFragment();
            fragments.add(wxwengZhangFragment);
            wxwengZhangFragment.setId(id);
        }
        wxwengzhangVP.notifyDataSetChanged();
    }
}
