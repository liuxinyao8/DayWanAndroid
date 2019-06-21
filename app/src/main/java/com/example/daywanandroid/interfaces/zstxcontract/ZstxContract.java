package com.example.daywanandroid.interfaces.zstxcontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;

public interface ZstxContract {
    interface View extends IBaseView{
        void Zstx(ZstxBean zstxBean);
    }
    interface Presenter extends IPrestenter<View>{
        void ZhiShi();
    }
}
