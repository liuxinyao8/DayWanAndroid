package com.example.daywanandroid.model.apis.dhapi;

import com.example.daywanandroid.model.bean.dhbean.DhBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface DhApi {
    @GET("navi/json")

    Flowable<DhBean>getDh();
}
