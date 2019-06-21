package com.example.daywanandroid.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daywanandroid.R;
import com.example.daywanandroid.activity.WxWv;
import com.example.daywanandroid.adapter.WxAdapter;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.wxcontract.WxwengzhangContract;
import com.example.daywanandroid.model.bean.wx.WxWengZhangBean;
import com.example.daywanandroid.presenter.wxpresenter.WXwengzhangPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WxwengZhangFragment extends BaseFragment implements WxwengzhangContract.View {

    @BindView(R.id.rv_Wx)
    RecyclerView rvWx;
    @BindView(R.id.smart_WX)
    SmartRefreshLayout smartWX;
    Unbinder unbinder;
    private int id;
    private int page;
    private ArrayList<WxWengZhangBean.DataBean.DatasBean> datasBeans;
    private WxAdapter wxAdapter;

    public void setId(int id) {
        this.id = id;
    }

    public WxwengZhangFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initData() {
        ((WXwengzhangPresenter)presenter).WxwngzhangP(id,page);
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new WXwengzhangPresenter();
    }

    @Override
    protected void initView() {
        rvWx.setLayoutManager(new LinearLayoutManager(getActivity()));
        datasBeans = new ArrayList<>();
        wxAdapter = new WxAdapter(datasBeans, getActivity());
        rvWx.setAdapter(wxAdapter);
        wxAdapter.Wx(new WxAdapter.Wx() {
            @Override
            public void OnLister(WxWengZhangBean.DataBean.DatasBean datasBean, int p) {
                String link = datasBean.getLink();
                String title = datasBean.getTitle();
                Intent intent = new Intent(getActivity(), WxWv.class);
                intent.putExtra("linkk",link);
                intent.putExtra("titlse",title);
                startActivity(intent);
            }
        });


        smartWX.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                refreshLayout.finishRefresh();
                initData();
                wxAdapter.notifyDataSetChanged();
            }
        });

        smartWX.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                refreshLayout.finishLoadMore();
                initData();
                wxAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wxweng_zhang;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Log.e("微信：",err);
    }

    @Override
    public void Wxwhengzhnag(WxWengZhangBean wxWengZhangBean) {
        List<WxWengZhangBean.DataBean.DatasBean> datas = wxWengZhangBean.getData().getDatas();
        datasBeans.addAll(datas);
        wxAdapter.notifyDataSetChanged();
    }
}
