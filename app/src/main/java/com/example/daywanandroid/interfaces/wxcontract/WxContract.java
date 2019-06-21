package com.example.daywanandroid.interfaces.wxcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.wx.WXtabBean;

public interface WxContract {
    interface View extends IBaseView{
        void Wxtab(WXtabBean wXtabBean);
    }
    interface Presenter extends IPrestenter<View>{
        void Wxwngzhang();
    }
}
