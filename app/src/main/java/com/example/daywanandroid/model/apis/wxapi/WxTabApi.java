package com.example.daywanandroid.model.apis.wxapi;

import com.example.daywanandroid.model.bean.wx.WXtabBean;
import com.example.daywanandroid.model.bean.wx.WxWengZhangBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WxTabApi {
    @GET("wxarticle/chapters/json")
    Flowable<WXtabBean> gettab();

    @GET("wxarticle/list/{cid}/{page}/json")
    Flowable<WxWengZhangBean>getWXwz(@Path("cid")int cid,@Path("page")int page);
}
