package com.example.daywanandroid.presenter.xmpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.xmcontract.XMContract;
import com.example.daywanandroid.interfaces.xmcontract.XMWengzhangContract;
import com.example.daywanandroid.model.bean.xmbean.XmBean;
import com.example.daywanandroid.model.bean.xmbean.XmwzBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class XmwengzhangPresenter extends BasePresenter<XMWengzhangContract.View>implements XMWengzhangContract.Presenter {

    @Override
    public void getprojectp(int page, int cid) {
        addSubscribe(HttpManager.getXmApi().getwengzhang(page,cid)
                .compose(RxUtils.<XmwzBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<XmwzBean>(myview){
                    @Override
                    public void onNext(XmwzBean xmwzBean) {
                        myview.getWenghzhangproject(xmwzBean);
                    }
                }));
    }
}
