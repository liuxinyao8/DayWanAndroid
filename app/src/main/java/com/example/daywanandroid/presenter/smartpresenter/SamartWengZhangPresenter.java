package com.example.daywanandroid.presenter.smartpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.smartContract.SmartWengZhangContract;
import com.example.daywanandroid.model.bean.smartbean.SmartBean;
import com.example.daywanandroid.model.bean.smartbean.SmartWengZhangBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class SamartWengZhangPresenter extends BasePresenter<SmartWengZhangContract.View>implements SmartWengZhangContract.Presenter {
    @Override
    public void WengZhangS(String k, int page) {
        addSubscribe(HttpManager.getSmartWengZhangApi().getSmartWZ(k,page)
                .compose(RxUtils.<SmartWengZhangBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<SmartWengZhangBean>(myview){
                    @Override
                    public void onNext(SmartWengZhangBean smartWengZhangBean) {
                        myview.swngzhang(smartWengZhangBean);
                    }
                }));
    }
}
