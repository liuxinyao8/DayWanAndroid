package com.example.daywanandroid.presenter.xmpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.xmcontract.XMContract;
import com.example.daywanandroid.model.bean.xmbean.XmBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class XmPresenter extends BasePresenter<XMContract.View>implements XMContract.Presenter {
    @Override
    public void getprojectp() {
        addSubscribe(HttpManager.getXmApi().getXm()
                .compose(RxUtils.<XmBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<XmBean>(myview){
                    @Override
                    public void onNext(XmBean xmBean) {
                        myview.getproject(xmBean);
                    }
                }));
    }
}
