package com.example.daywanandroid.model.apis.shouyeapi;

import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeBean;
import com.example.daywanandroid.model.bean.fragmnetbean.ShuoYeWengZhangBean;
import com.example.daywanandroid.model.likebean.CollectResData;
import com.example.daywanandroid.model.likebean.LikeBean;
import com.example.daywanandroid.model.likebean.MainBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ShouYeApi {
    @GET("banner/json")
    Flowable<ShuoYeBean>getShouYeBanner();

    @GET("article/list/{page}/json")
    Flowable<ShuoYeWengZhangBean>getShouyeWengZhang(@Path("page") int page);


    @GET("lg/collect/list/{page}/json")
    Flowable<MainBean> getLike1(@Path("page")int page);
       //收藏
    @POST("lg/collect/{id}/json")
    Flowable<LikeBean> getLike(@Path("id")int id);
      //取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    Flowable<CollectResData> unCollectList(@Path("id") int id);

}
