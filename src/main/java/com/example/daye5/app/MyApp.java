package com.example.daye5.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

public class MyApp extends Application {

    private static MyApp myApp;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }

    public static MyApp getInstance(){
        return myApp;
    }

    public static boolean isNightMode() {
        MyApp application = (MyApp) context.getApplicationContext();
        return application.isNightMode();
    }


    public static Context getContext(){
        return context;
    }

    /**
     * 初始化夜间模式
     */
    private void setNightMode() {
        boolean nightMode = UserInfoTools.isNightMode(this);
        AppCompatDelegate.setDefaultNightMode(nightMode ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

}
