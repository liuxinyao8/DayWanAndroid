package com.example.daywanandroid.interfaces.syContract;

import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;

public interface ShouYeContract {
    interface  View extends IBaseView{
        void ShuoYe(ShuoYeBean shuoYeBean);
        void ShouYeWZ(ShuoYeWengZhangBean shuoYeWengZhangBean);
        void getLike(LikeBean likeBean);
        void unCollectDataReturn(CollectResData collectResData);

    }
    interface  Presenter extends IPrestenter<View>{
        void getShuoYeBanner(int page);
        void getLikeP(int id);
        void unCollectData(int aid);
    }
}
