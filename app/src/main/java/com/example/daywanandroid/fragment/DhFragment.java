package com.example.daywanandroid.fragment;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.DhAdapter;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.dhcontract.DhContract;
import com.example.daywanandroid.model.bean.dhbean.DhBean;
import com.example.daywanandroid.presenter.dhpresenter.DhPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DhFragment extends BaseFragment implements DhContract.View {

    private boolean isScroll;
    @BindView(R.id.rv_dh)
    RecyclerView rvDh;
    private String[] color = {"#27AB0B", "#93335A", "#070405", "#F63406", "#375618", "#B19911", "#B522EA"};
    @BindView(R.id.dh_tablayout)
    VerticalTabLayout dhTablayout;
    private ArrayList<DhBean.DataBean> dataBeans;

    public DhFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        ((DhPresenter) presenter).getDh();
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new DhPresenter();
    }

    @Override
    protected void initView() {
        dataBeans = new ArrayList<>();
        rvDh.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dh;

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void getDaohao(final DhBean dhBean) {
        List<DhBean.DataBean> data = dhBean.getData();
        dataBeans.addAll(data);
        DhAdapter dhAdapter = new DhAdapter(dataBeans, getActivity());
        rvDh.setAdapter(dhAdapter);
        dhTablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LinearLayoutManager layoutManager =(LinearLayoutManager) rvDh.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        rvDh.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvDh.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                super.onScrollStateChanged(recyclerView, newState);
                dhTablayout.setTabSelected(top);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //这个主要是recyclerview滑动时让tab定位的方法

                                               /*recyclerView : 当前滚动的view
                                dx : 水平滚动距离
                                dy : 垂直滚动距离
                                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
                                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
                                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                                dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvDh.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                int bottom = layoutManager.findLastVisibleItemPosition();
                if (isScroll){
                    if (dy>0){
                        dhTablayout.setTabSelected(top);
                    }
                }
            }
        });
       dhTablayout.setTabAdapter(new TabAdapter() {
           @Override
           public int getCount() {
               return dataBeans.size();
           }

           @Override
           public ITabView.TabBadge getBadge(int position) {
               return null;
           }

           @Override
           public ITabView.TabIcon getIcon(int position) {
               return null;
           }

           @Override
           public ITabView.TabTitle getTitle(int position) {
               return new TabView.TabTitle.Builder()
                       .setContent(dataBeans.get(position).getName())
                       .build();
           }

           @Override
           public int getBackground(int position) {
               return 0;
           }
       });
    }

}
