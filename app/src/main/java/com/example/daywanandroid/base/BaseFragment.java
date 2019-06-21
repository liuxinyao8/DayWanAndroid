package com.example.daywanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.util.SystemUtils;


import butterknife.ButterKnife;
import butterknife.Optional;
import butterknife.Unbinder;

public abstract class BaseFragment<v extends IBaseView,p extends IPrestenter> extends Fragment implements IBaseView {
    protected Context context;
    protected p presenter;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        if (!SystemUtils.checkNetWork()){
            Toast.makeText(context, "加载网络失败，请重试！", Toast.LENGTH_SHORT).show();
        }else {
            context = getActivity();
            initView();
            presenter = FragmentPresenter();
            initData();
        }
        return view;
    }
    //初始化数据。
    protected abstract void initData();
//创建p层。
    protected abstract p FragmentPresenter();
//初始化布局
    protected abstract void initView();

//获取布局。
    protected abstract int getLayoutId();

    @Override
    public void onResume() {
        super.onResume();
        if (presenter!=null){
            presenter.attchView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detathView();
        }
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}

