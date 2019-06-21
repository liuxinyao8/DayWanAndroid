package com.example.daywanandroid.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

public class MyApplication extends android.app.Application {
//    private static final int  model= MODE_NIGHT_YES;
    public static MyApplication app;
    private static Resources sRes;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        init(this);
    }
    public static void init(Context context) {
        sRes = context.getResources();
    }
    /**
     * 切换 夜间模式
     * @param on true 夜间， false  日间
     */
    public static void updateNightMode(boolean on) {
        DisplayMetrics dm = sRes.getDisplayMetrics();
        Configuration config = sRes.getConfiguration();
        config.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        config.uiMode |= on ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
        sRes.updateConfiguration(config, dm);
    }

}
