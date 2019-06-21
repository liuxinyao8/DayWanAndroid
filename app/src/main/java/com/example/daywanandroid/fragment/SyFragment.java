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
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.daywanandroid.R;
import com.example.daywanandroid.activity.SYBannerWV;
import com.example.daywanandroid.activity.SYWZWV;
import com.example.daywanandroid.adapter.ShouYeAdapter;
import com.example.daywanandroid.base.BaseFragment;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.syContract.ShouYeContract;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.presenter.LikePresenter;
import com.example.daywanandroid.presenter.ShuoYe.SYbannerPresenter;
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
public class SyFragment extends BaseFragment implements ShouYeContract.View {

    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private int page;
    @BindView(R.id.rv1_ShouYe)
    RecyclerView rv1ShouYe;
    private ArrayList<ShuoYeBean.DataBean> dataBeans;
    private ArrayList<ShuoYeWengZhangBean.DataBean.DatasBean> datasBeans;
    private ShouYeAdapter shouYeAdapter;

    public SyFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        ((SYbannerPresenter) presenter).getShuoYeBanner(page);

    }

    @Override
    protected IPrestenter FragmentPresenter() {
        return new SYbannerPresenter();
    }

    @Override
    protected void initView() {
        dataBeans = new ArrayList<>();
        datasBeans = new ArrayList<>();
        rv1ShouYe.setLayoutManager(new LinearLayoutManager(getActivity()));
        shouYeAdapter = new ShouYeAdapter(dataBeans, datasBeans, getActivity());
        rv1ShouYe.setAdapter(shouYeAdapter);


        shouYeAdapter.setMybanner(new ShouYeAdapter.Mybanner() {
            @Override
            public void onBannerlisenter(ShuoYeBean.DataBean dataBean, int p) {
                String url = dataBean.getUrl();
                Intent intent = new Intent(getActivity(), SYBannerWV.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

        shouYeAdapter.setWengzhang(new ShouYeAdapter.Wengzhang() {
            @Override
            public void onBannerlisenter(Boolean b,ShuoYeWengZhangBean.DataBean.DatasBean datasBean, int p) {
                if (b){
                    ((SYbannerPresenter)presenter).getLikeP(datasBean.getId());
                }else {
                    ((SYbannerPresenter)presenter).unCollectData(datasBean.getId());
                }
            }
        });
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                dataBeans.clear();
                datasBeans.clear();
                refreshLayout.finishRefresh();
                shouYeAdapter.notifyDataSetChanged();
                initData();
            }
        });

        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                page++;
                refreshLayout.finishLoadMore();
                shouYeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sy;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Log.e("首页：", err);
    }


    @Override
    public void ShuoYe(ShuoYeBean shuoYeBean) {
        List<ShuoYeBean.DataBean> data = shuoYeBean.getData();
        dataBeans.addAll(data);
        shouYeAdapter.notifyDataSetChanged();

    }

    @Override
    public void ShouYeWZ(ShuoYeWengZhangBean shuoYeWengZhangBean) {
        List<ShuoYeWengZhangBean.DataBean.DatasBean> datas = shuoYeWengZhangBean.getData().getDatas();
        datasBeans.addAll(datas);
        shouYeAdapter.notifyDataSetChanged();
    }
    //收藏数据返回。
    @Override
    public void getLike(LikeBean likeBean) {
        if (likeBean.getErrorCode()==0){
            Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
            if(datasBeans.size() > 0)
                datasBeans.remove(0);
        }else {
            if(datasBeans.size() > 0){
                datasBeans.remove(0).setCollect(false);
                shouYeAdapter.notifyDataSetChanged();
            }
            Toast.makeText(context,"收藏失败："+likeBean.getErrorMsg(),Toast.LENGTH_SHORT).show();
        }
    }


    //收藏失败返回
    @Override
    public void unCollectDataReturn(CollectResData collectResData) {
        if(collectResData.getErrorCode() == 0){
            Toast.makeText(context,"取消收藏成功",Toast.LENGTH_SHORT).show();
            if(datasBeans.size() > 0)
                datasBeans.remove(0);
        }else{
            if(datasBeans.size() > 0){
                datasBeans.remove(0).setCollect(true);
                shouYeAdapter.notifyDataSetChanged();
            }
            Toast.makeText(context,"取消收藏失败："+collectResData.getErrorMsg(),Toast.LENGTH_SHORT).show();
        }
    }

}
