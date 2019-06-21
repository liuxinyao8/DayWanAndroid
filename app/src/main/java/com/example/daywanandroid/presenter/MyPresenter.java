package com.example.daywanandroid.presenter;


import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.news.NewContract;
import com.example.daywanandroid.model.bean.IndexBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class MyPresenter extends BasePresenter<NewContract.View> implements NewContract.Presenter {

    @Override
    public void getIndex() {
        addSubscribe(HttpManager.getMyApi().getIndexData()
                .compose(RxUtils.<IndexBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<IndexBean>(myview){
                    @Override
                    public void onNext(IndexBean indexBean) {
                        myview.getIndexReturn(indexBean);
                    }
                }));
    }
}
