package com.example.daywanandroid.model.http;
import com.example.daywanandroid.app.Constant;
import com.example.daywanandroid.model.apis.MyApi;
import com.example.daywanandroid.model.apis.smartapi.SmartApi;
import com.example.daywanandroid.model.apis.dhapi.DhApi;
import com.example.daywanandroid.model.apis.loginApi.login_MyApi;
import com.example.daywanandroid.model.apis.shouyeapi.ShouYeApi;
import com.example.daywanandroid.model.apis.smartapi.SmartWengZhangApi;
import com.example.daywanandroid.model.apis.wxapi.WxTabApi;
import com.example.daywanandroid.model.apis.xmapi.XmApi;
import com.example.daywanandroid.model.apis.zstxapi.WengZhangApi;
import com.example.daywanandroid.model.apis.zstxapi.Zxtxapi;
import com.example.daywanandroid.util.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
private static MyApi myApi;
private static login_MyApi login_myApi;
private static ShouYeApi shouYeApi;
private static Zxtxapi zxtxapi;
private static WengZhangApi wengZhangApi;
private static XmApi xmApi;
private static WxTabApi wxTabApi;
private static SmartApi smartApi;
private static DhApi dhApi;
private static SmartWengZhangApi smartWengZhangApi;
    private static Cache cache;
    //创建Retrofit对象
    private static Retrofit getRetrofit(String URL){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpClient())
                .build();
        return retrofit;
    }
    /**
     * 创建带缓存的HttpClient对象
     * @return
     */
    private static OkHttpClient getOkhttpClient() {
        //本地缓存文件
        File file = new File(Constant.PATH_CACHE);
        //设置缓存文件的大小100M
        cache = new Cache(file, 1024 * 1024 * 100);
        return new OkHttpClient.Builder()
                .cache(cache)
                .cookieJar(cookieJar)
                .addInterceptor(new Myintercepter())
                .addNetworkInterceptor(new Myintercepter())
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }
    //获取相关的网络接口 baseURL基础地址  tCla 用来做数据加载的接口类
    private static synchronized <T> T getServerApis(String baseUrl,Class<T> tCla){
            return getRetrofit(baseUrl).create(tCla);
    }
    //登陆

    public static login_MyApi getWanLogin(){
        synchronized (HttpManager.class){
            if (login_myApi==null){
                synchronized (HttpManager.class){
                    login_myApi=getServerApis(Constant.Base_Wan_url,login_MyApi.class);
                }
            }
        }
        return login_myApi;
    }

public static MyApi getMyApi(){
        synchronized (HttpManager.class){
            if (myApi==null){
                synchronized (HttpManager.class){
                    myApi=getServerApis(Constant.URl,MyApi.class);
                }
            }
        }
        return myApi;
}


//首页
    public static ShouYeApi getShouYe(){
        synchronized (HttpManager.class){
            if (shouYeApi==null){
                synchronized (HttpManager.class){
                    shouYeApi=getServerApis(Constant.Base_ShouYe_Url,ShouYeApi.class);
                }
            }
        }
        return shouYeApi;
    }
//知识体系
    public static Zxtxapi getzxtxapi(){
        synchronized (HttpManager.class){
            if (zxtxapi==null){
                synchronized (HttpManager.class){
                    zxtxapi=getServerApis(Constant.Base_ShouYe_Url,Zxtxapi.class);
                }
            }
        }
        return zxtxapi;
    }
//体系文章。
    public static WengZhangApi getWengZhangapi(){
        synchronized (HttpManager.class){
            if (wengZhangApi==null){
                synchronized (HttpManager.class){
                    wengZhangApi=getServerApis(Constant.Base_ShouYe_Url,WengZhangApi.class);
                }
            }
        }
        return wengZhangApi;
    }

//项目
    public static XmApi getXmApi(){
        synchronized (HttpManager.class){
            if (xmApi==null){
                synchronized (HttpManager.class){
                    xmApi=getServerApis(Constant.Base_ShouYe_Url,XmApi.class);
                }
            }
        }
        return xmApi;
    }

    //搜索热词。
    public static SmartApi getSmartApi(){
        synchronized (HttpManager.class){
            if (smartApi==null){
                synchronized (HttpManager.class){
                   smartApi =getServerApis(Constant.Base_ShouYe_Url,SmartApi.class);
                }
            }
        }
        return smartApi;
    }

    //微信公众号。
    public static WxTabApi getWxTabApi(){
        synchronized (HttpManager.class){
            if (wxTabApi==null){
                synchronized (HttpManager.class){
                    wxTabApi =getServerApis(Constant.Base_ShouYe_Url,WxTabApi.class);
                }
            }
        }
        return wxTabApi;
    }

//导航
    public static DhApi getDhApi(){
        synchronized (HttpManager.class){
            if (dhApi==null){
                synchronized (HttpManager.class){
                    dhApi =getServerApis(Constant.Base_ShouYe_Url,DhApi.class);
                }
            }
        }
        return dhApi;
    }


//搜索热词
    public static SmartWengZhangApi getSmartWengZhangApi(){
        synchronized (HttpManager.class){
            if (smartWengZhangApi==null){
                synchronized (HttpManager.class){
                    smartWengZhangApi =getServerApis(Constant.Base_ShouYe_Url,SmartWengZhangApi.class);
                }
            }
        }
        return smartWengZhangApi;
    }


    //Cookie
    private static CookieJar cookieJar = new CookieJar() {

        private final Map<String,List<Cookie>> cookieMap = new HashMap<String,List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            String host = url.host();
            List<Cookie> cookieList = cookieMap.get(host);
            if(cookieList != null){
                cookieMap.remove(host);
            }
            cookieMap.put(host,cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookieList = cookieMap.get(url.host());
            return cookieList != null ? cookieList : new ArrayList<Cookie>();
        }
    };

    //拦截器的实现类
    private static class Myintercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }
}
