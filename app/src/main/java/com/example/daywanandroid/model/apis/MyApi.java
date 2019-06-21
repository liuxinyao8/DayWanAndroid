package com.example.daywanandroid.model.apis;


import com.example.daywanandroid.model.bean.IndexBean;
import com.example.daywanandroid.model.likebean.LikeBean;

import io.reactivex.Flowable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApi {
@GET("index/index")
    Flowable<IndexBean>getIndexData();

}
