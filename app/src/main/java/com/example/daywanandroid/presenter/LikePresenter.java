package com.example.daywanandroid.presenter;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.likecontract.LikeContract;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.model.likebean.MainBean;
import com.example.daywanandroid.util.RxUtils;

public class LikePresenter extends BasePresenter<LikeContract.View> implements LikeContract.Presenter {
    @Override
    public void getLike1() {
        addSubscribe(HttpManager.getShouYe().getLike1(0)
                .compose(RxUtils.<MainBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<MainBean>(myview){
                    @Override
                    public void onNext(MainBean mainBean) {
                        myview.getLike1(mainBean);
                    }
                }));
    }

    @Override
    public void unCollectData(int aid) {
        addSubscribe(HttpManager.getShouYe().unCollectList(aid)
                .compose(RxUtils.<CollectResData> rxScheduler())
                .subscribeWith(new CommonSubscriber<CollectResData>(myview){
                    @Override
                    public void onNext(CollectResData collectResData) {
                        myview.unCollectDataReturn(collectResData);
                    }
                }));
    }


}
