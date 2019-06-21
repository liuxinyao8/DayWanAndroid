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
import com.example.daywanandroid.activity.Tixiwengzhang;
import com.example.daywanandroid.adapter.ZhiShiAdapter;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.zstxcontract.ZstxContract;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;
import com.example.daywanandroid.presenter.ShuoYe.SYbannerPresenter;
import com.example.daywanandroid.presenter.zxtxpresenter.ZhiShiPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZsFragment extends BaseFragment implements ZstxContract.View {


    @BindView(R.id.rv_ZhiShi)
    RecyclerView rvZhiShi;
    @BindView(R.id.smart_ZhiShi)
    SmartRefreshLayout smartZhiShi;
    Unbinder unbinder;
    private ArrayList<ZstxBean.DataBean> zstxBeans;
    private ArrayList<ZstxBean.DataBean.ChildrenBean> childrenBeans;
    private ZhiShiAdapter zhiShiAdapter;

    public ZsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        ((ZhiShiPresenter) presenter).ZhiShi();
    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new ZhiShiPresenter();
    }

    @Override
    protected void initView() {
        rvZhiShi.setLayoutManager(new LinearLayoutManager(getContext()));
        zstxBeans = new ArrayList<>();
        childrenBeans=new ArrayList<>();

        zhiShiAdapter = new ZhiShiAdapter(zstxBeans,getActivity());
        rvZhiShi.setAdapter(zhiShiAdapter);
        zhiShiAdapter.setZhishi(new ZhiShiAdapter.Zhishi() {
            @Override
            public void onClick(ZstxBean.DataBean dataBean, int p) {
                Intent intent = new Intent(getActivity(), Tixiwengzhang.class);
//                List<ZstxBean.DataBean.ChildrenBean> children = dataBean.getChildren();
//                ArrayList<ZstxBean.DataBean.ChildrenBean> childrenBeans = new ArrayList<>();
//                childrenBeans.addAll(children);
                intent.putExtra("data",zstxBeans);
//                intent.putExtra("child",  childrenBeans);
                startActivity(intent);
            }
        });



        smartZhiShi.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
              zstxBeans.clear();
              refreshLayout.finishRefresh();
              zhiShiAdapter.notifyDataSetChanged();
              initData();
            }
        });
        smartZhiShi.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                zhiShiAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zs;
    }

    @Override
    public void Zstx(ZstxBean zstxBean) {
        List<ZstxBean.DataBean> data = zstxBean.getData();
        zstxBeans.addAll(data);
//        for (int i = 0; i < zstxBeans.size(); i++) {
//            List<ZstxBean.DataBean.ChildrenBean> children = data.get(i).getChildren();
//            childrenBeans.addAll(children);
//        }
         zhiShiAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Log.e("知识体系：",err);
    }

}
