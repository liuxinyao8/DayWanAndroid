package com.example.daywanandroid.interfaces.news;


import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.IndexBean;

public interface NewContract {
    //主页数据
    interface View extends IBaseView {
void getIndexReturn(IndexBean indexBean);
    }
    //新闻的Presenter层接口定义
interface Presenter extends IPrestenter<View> {
        void getIndex();
}
}
