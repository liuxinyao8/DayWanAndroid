package com.example.daywanandroid.interfaces.dhcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.dhbean.DhBean;

public interface DhContract {
    interface View extends IBaseView{
        void getDaohao(DhBean dhBean);
    }
    interface  Presenter extends IPrestenter<View>{
        void getDh();
    }
}
