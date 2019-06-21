package com.example.daywanandroid.model.apis.smartapi;

import com.example.daywanandroid.model.bean.smartbean.SmartBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface SmartApi {
    @GET("hotkey/json")
    Flowable<SmartBean>getSmart();
}
