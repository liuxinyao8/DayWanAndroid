package com.example.daywanandroid.app;

import java.io.File;

public class Constant {
public static final  String URl="http://cdwan.cn:8360/api/";
    public static final String Base_Wan_url = "https://www.wanandroid.com/";
    public static final String Base_ShouYe_Url = "https://www.wanandroid.com/";

    //网络缓存的地址
public static final String PATH_DATA= MyApplication.app
        .getCacheDir().getAbsolutePath()+ File.separator+"ShiXun";
public static final String PATH_CACHE=PATH_DATA+"/DayWanAndroid";


}
