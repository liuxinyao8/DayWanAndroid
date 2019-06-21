package com.example.daywanandroid.model.apis.zstxapi;

import com.example.daywanandroid.model.bean.zxtxBean.WengZhangBean;
import com.example.daywanandroid.model.bean.zxtxBean.ZstxBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WengZhangApi {
    @GET("article/list/{page}/json?")
    Flowable<WengZhangBean> getZSWengZhang(@Path("page")int page, @Query("cid") int id);
}
