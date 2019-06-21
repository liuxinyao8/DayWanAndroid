package com.example.daywanandroid.interfaces.smartContract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.smartbean.SmartWengZhangBean;

public interface SmartWengZhangContract {
    interface View extends IBaseView{
        void swngzhang(SmartWengZhangBean smartWengZhangBean);
    }
    interface  Presenter extends IPrestenter<View>{
        void WengZhangS(String k,int page);
    }
}
