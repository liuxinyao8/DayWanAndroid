package com.example.daywanandroid.presenter.dhpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.dhcontract.DhContract;
import com.example.daywanandroid.model.bean.dhbean.DhBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class DhPresenter extends BasePresenter<DhContract.View>implements DhContract.Presenter {
    @Override
    public void getDh() {
        addSubscribe(HttpManager.getDhApi().getDh()
                .compose(RxUtils.<DhBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<DhBean>(myview){
                    @Override
                    public void onNext(DhBean dhBean) {
                        myview.getDaohao(dhBean);
                    }
                }));
    }
}
