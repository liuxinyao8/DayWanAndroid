package com.example.daywanandroid.interfaces.login;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.loginbean.RegistBean;

public interface RegistContract {
    interface View extends IBaseView {

        void registerReturn(RegistBean registerBean);

    }

    interface Presenter extends IPrestenter<View> {

        void register(String username,String password,String repassword);

    }
}
