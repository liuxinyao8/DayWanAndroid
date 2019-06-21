package com.example.daywanandroid.presenter.wxpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.wxcontract.WxContract;
import com.example.daywanandroid.interfaces.wxcontract.WxwengzhangContract;
import com.example.daywanandroid.model.bean.IndexBean;
import com.example.daywanandroid.model.bean.wx.WxWengZhangBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class WXwengzhangPresenter extends BasePresenter<WxwengzhangContract.View> implements WxwengzhangContract.Presenter {

    @Override
    public void WxwngzhangP(int cid, int page) {
        addSubscribe(HttpManager.getWxTabApi().getWXwz(cid,page)
                .compose(RxUtils.<WxWengZhangBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<WxWengZhangBean>(myview){
                    @Override
                    public void onNext(WxWengZhangBean wxWengZhangBean) {
                        myview.Wxwhengzhnag(wxWengZhangBean);
                    }
                }));
    }
}
