package com.example.daywanandroid.interfaces.login;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.loginbean.LoginBean;

public interface LoginContract {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
    }

    interface Persenter extends IPrestenter<View> {
        void login(String username,String password);
    }
}
