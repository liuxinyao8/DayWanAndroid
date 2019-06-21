package com.example.daywanandroid.presenter.smartpresenter;


import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.smartContract.SmartContract;
import com.example.daywanandroid.model.bean.smartbean.SmartBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class SamrtPresenter extends BasePresenter<SmartContract.View> implements SmartContract.Presenter {


    @Override
    public void getSmartP() {
        addSubscribe(HttpManager.getSmartApi().getSmart()
                .compose(RxUtils.<SmartBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<SmartBean>(myview){
                    @Override
                    public void onNext(SmartBean smartBean) {
                        myview.getSmart(smartBean);
                    }
                }));
    }
}
