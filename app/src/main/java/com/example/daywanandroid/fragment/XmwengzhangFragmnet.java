package com.example.daywanandroid.fragment;


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
import com.example.daywanandroid.adapter.XmFragmnetAdapter;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.xmcontract.XMWengzhangContract;
import com.example.daywanandroid.model.bean.xmbean.XmwzBean;
import com.example.daywanandroid.presenter.xmpresenter.XmPresenter;
import com.example.daywanandroid.presenter.xmpresenter.XmwengzhangPresenter;
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
public class XmwengzhangFragmnet extends BaseFragment implements XMWengzhangContract.View {

    @BindView(R.id.rv_Xm)
    RecyclerView rvXm;
    @BindView(R.id.smart_XM)
    SmartRefreshLayout smartXM;

    private int id;
private int page;
    private ArrayList<XmwzBean.DataBean.DatasBean> datasBeans;
    private XmFragmnetAdapter xmFragmnetAdapter;

    public void setId(int id) {
        this.id = id;
    }

    public XmwengzhangFragmnet() {
        // Required empty public constructor
    }


    @Override
    protected void initData() {
        ((XmwengzhangPresenter)presenter).getprojectp(page,id);
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new XmwengzhangPresenter();
    }

    @Override
    protected void initView() {
        datasBeans = new ArrayList<>();
        rvXm.setLayoutManager(new LinearLayoutManager(getActivity()));
        xmFragmnetAdapter = new XmFragmnetAdapter(datasBeans, getActivity());
        rvXm.setAdapter(xmFragmnetAdapter);
        smartXM.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                refreshLayout.finishRefresh();
                initData();
                xmFragmnetAdapter.notifyDataSetChanged();
            }
        });

        smartXM.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                page++;
                refreshLayout.finishLoadMore();
                xmFragmnetAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xmwengzhang_fragmnet;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Log.e("项目文章",err);
    }

    @Override
    public void getWenghzhangproject(XmwzBean xmwzBean) {
        List<XmwzBean.DataBean.DatasBean> datas = xmwzBean.getData().getDatas();
        datasBeans.addAll(datas);
        xmFragmnetAdapter.notifyDataSetChanged();
    }
}
