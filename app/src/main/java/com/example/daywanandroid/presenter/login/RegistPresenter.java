package com.example.daywanandroid.presenter.login;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.login.LoginContract;
import com.example.daywanandroid.interfaces.login.RegistContract;
import com.example.daywanandroid.model.bean.loginbean.LoginBean;
import com.example.daywanandroid.model.bean.loginbean.RegistBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class RegistPresenter extends BasePresenter<RegistContract.View>implements RegistContract.Presenter {


    @Override
    public void register(String username, String password, String repassword) {
        addSubscribe(HttpManager.getWanLogin().regist(username,password,repassword)
                .compose(RxUtils.<RegistBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<RegistBean>(myview){
                    @Override
                    public void onNext(RegistBean registBean) {
                        myview.registerReturn(registBean);
                    }
                }));
    }
}
