package com.example.daywanandroid.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.LikeAdapter;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.likecontract.LikeContract;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.model.likebean.MainBean;
import com.example.daywanandroid.presenter.LikePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class link extends BaseActivity implements LikeContract.View {
    @BindView(R.id.rv1_like)
    RecyclerView rv1Like;
    @BindView(R.id.smart_like)
    SmartRefreshLayout smartLike;
    private ArrayList<MainBean.DataBean.DatasBean> datasBeans;
    private LikeAdapter likeAdapter;
    private  List<MainBean.DataBean.DatasBean> requestList;


    @Override
    protected void initData() {
        ((LikePresenter)presenter).getLike1();
    }

    @Override
    protected IPrestenter createPresenter() {
        return new LikePresenter();
    }

    @Override
    protected void initView() {
        rv1Like.setLayoutManager(new LinearLayoutManager(this));
        datasBeans = new ArrayList<>();
        requestList=new ArrayList<>();
        likeAdapter = new LikeAdapter(datasBeans, this);
        rv1Like.setAdapter(likeAdapter);
        rv1Like.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        /*smartLike.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                refreshLayout.finishRefresh();
                likeAdapter.notifyDataSetChanged();
                initData();
            }
        });
        smartLike.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                initData();
            }
        });
*/
        likeAdapter.setItemClick(new LikeAdapter.ItemClick() {
            @Override
            public void onClick(View v, MainBean.DataBean.DatasBean datasBean) {
                requestList.add(datasBean);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_link;
    }

    @Override
    public void getLike1(MainBean mainBean) {
        datasBeans.addAll(mainBean.getData().getDatas());
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void unCollectDataReturn(CollectResData collectResData) {
        if(collectResData.getErrorCode() == 0){
            Toast.makeText(context,"取消收藏成功",Toast.LENGTH_SHORT).show();
            if(requestList.size() > 0)
                requestList.remove(0);
        }else{
            if(requestList.size() > 0){
                requestList.remove(0).setCollect(true);
                likeAdapter.notifyDataSetChanged();
            }
            Toast.makeText(context,"取消收藏失败："+collectResData.getErrorMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        requestList.clear();
        super.onDestroy();
    }
}
