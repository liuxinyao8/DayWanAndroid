package com.example.daywanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.daywanandroid.model.bean.wx.WXtabBean;
import com.example.daywanandroid.model.bean.xmbean.XmBean;

import java.util.ArrayList;
import java.util.List;

public class WxwengzhangVP extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>list;
    private List<WXtabBean.DataBean> data;

    public WxwengzhangVP(FragmentManager fm, ArrayList<Fragment> list, List<WXtabBean.DataBean> data) {
        super(fm);
        this.list = list;
        this.data = data;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }
}
