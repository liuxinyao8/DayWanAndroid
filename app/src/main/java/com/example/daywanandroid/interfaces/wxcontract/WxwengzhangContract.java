package com.example.daywanandroid.interfaces.wxcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.wx.WXtabBean;
import com.example.daywanandroid.model.bean.wx.WxWengZhangBean;

public interface WxwengzhangContract {
    interface View extends IBaseView{
        void Wxwhengzhnag(WxWengZhangBean wxWengZhangBean);
    }
    interface Presenter extends IPrestenter<View>{
        void WxwngzhangP(int cid,int page);
    }
}
