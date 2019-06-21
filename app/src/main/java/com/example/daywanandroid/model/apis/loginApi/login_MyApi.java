package com.example.daywanandroid.model.apis.loginApi;

import com.example.daywanandroid.model.bean.loginbean.LoginBean;
import com.example.daywanandroid.model.bean.loginbean.RegistBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface login_MyApi {
    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> Login(@Field("username")String username, @Field("password")String password);


    @POST("user/register")
    @FormUrlEncoded
    Flowable<RegistBean> regist(@Field("username")String username, @Field("password")String password, @Field("repassword") String repassword);
}
