package com.example.daywanandroid.presenter.wxpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.wxcontract.WxContract;
import com.example.daywanandroid.model.bean.IndexBean;
import com.example.daywanandroid.model.bean.wx.WXtabBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class WXtabPresenter extends BasePresenter<WxContract.View> implements WxContract.Presenter {
    @Override
    public void Wxwngzhang() {
        addSubscribe(HttpManager.getWxTabApi().gettab()
                .compose(RxUtils.<WXtabBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<WXtabBean>(myview){
                    @Override
                    public void onNext(WXtabBean wXtabBean) {
                        myview.Wxtab(wXtabBean);
                    }
                }));
    }
}
