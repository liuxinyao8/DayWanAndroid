package com.example.daywanandroid.interfaces.xmcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.xmbean.XmBean;

public interface XMContract {
    interface View extends IBaseView{
      void   getproject(XmBean xmBean);
    }
    interface Presenter extends IPrestenter<View>{
        void getprojectp();
    }
}
