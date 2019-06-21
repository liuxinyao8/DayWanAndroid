package com.example.daywanandroid.presenter.ShuoYe;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.syContract.ShouYeContract;
import com.example.daywanandroid.model.bean.IndexBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.util.RxUtils;

public class SYbannerPresenter extends BasePresenter<ShouYeContract.View>implements ShouYeContract.Presenter {

    @Override
    public void getShuoYeBanner(int page) {
        addSubscribe(HttpManager.getShouYe().getShouYeBanner()
                .compose(RxUtils.<ShuoYeBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ShuoYeBean>(myview){
                    @Override
                    public void onNext(ShuoYeBean shuoYeBean) {
                        myview.ShuoYe(shuoYeBean);
                    }
                }));

        addSubscribe(HttpManager.getShouYe().getShouyeWengZhang(page)
                .compose(RxUtils.<ShuoYeWengZhangBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ShuoYeWengZhangBean>(myview){
                    @Override
                    public void onNext(ShuoYeWengZhangBean shuoYeWengZhangBean) {
                        myview.ShouYeWZ(shuoYeWengZhangBean);
                    }
                }));
    }

    @Override
    public void getLikeP(int id) {
        addSubscribe(HttpManager.getShouYe().getLike(id)
                .compose(RxUtils.<LikeBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<LikeBean>(myview){
                    @Override
                    public void onNext(LikeBean likeBean) {
                        myview.getLike(likeBean);
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
