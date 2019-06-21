package com.example.daywanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;

import java.util.ArrayList;
import java.util.List;

public class TixiwengzhangVP extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>list;
    private List<ZstxBean.DataBean.ChildrenBean> children;

    public TixiwengzhangVP(FragmentManager fm, ArrayList<Fragment> list, List<ZstxBean.DataBean.ChildrenBean> children) {
        super(fm);
        this.list = list;
        this.children = children;
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
        return children.get(position).getName();
    }
}
