package com.example.daywanandroid.interfaces.likecontract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.model.likebean.MainBean;

public interface LikeContract {
    interface View extends IBaseView{
        void getLike1(MainBean mainBean);
        void unCollectDataReturn(CollectResData collectResData);

    }
    interface Presenter extends IPrestenter<View>{
        void getLike1();
        void unCollectData(int aid);
    }
}
