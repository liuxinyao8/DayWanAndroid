package com.example.daywanandroid.interfaces.zstxcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.zxtxBean.WengZhangBean;

public interface WengZahngContract {
    interface View extends IBaseView{
        void WhengZhang(WengZhangBean wengZhangBean);
    }
    interface Presenter extends IPrestenter<View>{
        void getWhengZhang(int page,int id);
    }
}
