package com.example.daywanandroid.interfaces.xmcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.xmbean.XmBean;
import com.example.daywanandroid.model.bean.xmbean.XmwzBean;

public interface XMWengzhangContract {
    interface View extends IBaseView{
      void   getWenghzhangproject(XmwzBean xmwzBean);
    }
    interface Presenter extends IPrestenter<View>{
        void getprojectp(int page,int cid);
    }
}
