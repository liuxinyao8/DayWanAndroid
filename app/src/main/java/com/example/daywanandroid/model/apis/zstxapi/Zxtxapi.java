package com.example.daywanandroid.model.apis.zstxapi;

import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface Zxtxapi {
    @GET("tree/json")
    Flowable<ZstxBean>getZStx();
}
