package com.example.daywanandroid.presenter.login;

import com.example.daywanandroid.base.BasePresenter;
import com.example.daywanandroid.compentent.CommonSubscriber;
import com.example.daywanandroid.interfaces.login.LoginContract;
import com.example.daywanandroid.model.bean.loginbean.LoginBean;
import com.example.daywanandroid.model.http.HttpManager;
import com.example.daywanandroid.util.RxUtils;

public class LoginPresenter extends BasePresenter<LoginContract.View>implements LoginContract.Persenter {
    @Override
    public void login(String username, String password) {
        addSubscribe(HttpManager.getWanLogin().Login(username,password)
                .compose(RxUtils.<LoginBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(myview){
                    @Override
                    public void onNext(LoginBean loginBean) {
                        myview.loginReturn(loginBean);
                    }
                }));
    }
}
