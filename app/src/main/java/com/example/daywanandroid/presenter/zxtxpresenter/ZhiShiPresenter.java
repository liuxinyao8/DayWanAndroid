package com.example.daywanandroid.presenter.zxtxpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.zstxcontract.ZstxContract;
import com.example.daywanandroid.model.bean.IndexBean;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class ZhiShiPresenter extends BasePresenter<ZstxContract.View>implements ZstxContract.Presenter {
    @Override
    public void ZhiShi() {
        addSubscribe(HttpManager.getzxtxapi().getZStx()
                .compose(RxUtils.<ZstxBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ZstxBean>(myview){
                    @Override
                    public void onNext(ZstxBean zstxBean) {
                        myview.Zstx(zstxBean);
                    }
                }));
    }
}
