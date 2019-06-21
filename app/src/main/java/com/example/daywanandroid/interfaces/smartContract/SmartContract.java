package com.example.daywanandroid.interfaces.smartContract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.smartbean.SmartBean;

public interface SmartContract {
    interface View extends IBaseView {
        void getSmart(SmartBean smartBean);
    }
    interface  Presenter extends IPrestenter<View> {
       void getSmartP();
    }
}
