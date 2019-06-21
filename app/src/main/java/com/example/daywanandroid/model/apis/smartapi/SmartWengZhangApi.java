package com.example.daywanandroid.model.apis.smartapi;

import com.example.daywanandroid.model.bean.smartbean.SmartWengZhangBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SmartWengZhangApi {
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Flowable<SmartWengZhangBean>getSmartWZ(@Field("k")String k, @Path("page")int page);
}
