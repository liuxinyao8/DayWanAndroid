package com.example.daywanandroid.presenter.zxtxpresenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.zstxcontract.WengZahngContract;
import com.example.daywanandroid.model.bean.zxtxBean.WengZhangBean;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class WengZhangPresenter extends BasePresenter<WengZahngContract.View>implements WengZahngContract.Presenter {
    @Override
    public void getWhengZhang(int page,int id) {
        addSubscribe(HttpManager.getWengZhangapi().getZSWengZhang(page,id)
                .compose(RxUtils.<WengZhangBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<WengZhangBean>(myview){
                    @Override
                    public void onNext(WengZhangBean wengZhangBean) {
                        myview.WhengZhang(wengZhangBean);
                    }
                }));
    }
}
