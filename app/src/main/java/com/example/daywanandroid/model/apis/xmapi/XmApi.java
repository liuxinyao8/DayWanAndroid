package com.example.daywanandroid.model.apis.xmapi;

import com.example.daywanandroid.model.bean.xmbean.XmBean;
import com.example.daywanandroid.model.bean.xmbean.XmwzBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface XmApi {
    @GET("project/tree/json")
    Flowable<XmBean>getXm();

    @GET("project/list/{page}/json?")
    Flowable<XmwzBean>getwengzhang(@Path("page")int page, @Query("cid")int cid);
}
