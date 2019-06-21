package com.example.daywanandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.TX_WengZhangAdapter;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.zstxcontract.WengZahngContract;
import com.example.daywanandroid.model.bean.zxtxBean.WengZhangBean;
import com.example.daywanandroid.presenter.zxtxpresenter.WengZhangPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TxwengzhangFragment extends BaseFragment implements WengZahngContract.View {
    @BindView(R.id.rv_WehngZhang)
    RecyclerView rvWehngZhang;
    @BindView(R.id.smart_WengZhang)
    SmartRefreshLayout smartWengZhang;
    private int page;
    private int id;
    private ArrayList<WengZhangBean.DataBean.DatasBean> datasBeans;
    private TX_WengZhangAdapter txWengZhangAdapter;

    public void setId(int id) {
        this.id = id;
    }

    public TxwengzhangFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        ((WengZhangPresenter) presenter).getWhengZhang(page, id);
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new WengZhangPresenter();
    }

    @Override
    protected void initView() {
rvWehngZhang.setLayoutManager(new LinearLayoutManager(getActivity()));
        datasBeans = new ArrayList<>();
        txWengZhangAdapter = new TX_WengZhangAdapter(datasBeans, getActivity());
        rvWehngZhang.setAdapter(txWengZhangAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_txwengzhang;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Log.e("知识体系下的文章:",err);
    }

    @Override
    public void WhengZhang(WengZhangBean wengZhangBean) {
        List<WengZhangBean.DataBean.DatasBean> datas = wengZhangBean.getData().getDatas();
        datasBeans.addAll(datas);
        txWengZhangAdapter.notifyDataSetChanged();
    }

}
