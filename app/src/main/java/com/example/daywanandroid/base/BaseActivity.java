package com.example.daywanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.daywanandroid.R;
import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.util.SystemUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<v extends IBaseView,p extends IPrestenter>extends
        AppCompatActivity implements IBaseView {
    protected Context context;
protected p presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
if (!SystemUtils.checkNetWork()) {
    //自定义布局实现无网络状态的提示
    View view = LayoutInflater.from(this).inflate(R.layout.layout_network_error, null);
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams
            (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    addContentView(view,params);
}else {
    context=this;
    initView();
    presenter=createPresenter();
    initData();
}
    }
    //初始化数据
    protected abstract void initData();
    //创建P
    protected abstract p createPresenter();
    //初始化布局
    protected abstract void initView();

    //获取布局
    protected abstract int getLayout();

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter!=null){
            presenter.attchView(this);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detathView();
        }
        if (unbinder!=null){
            unbinder.unbind();
        }
    }

}
