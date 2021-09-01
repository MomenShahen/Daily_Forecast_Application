package com.test.dailyforecast.utils;

import android.app.Application;
import android.content.Context;

import com.test.dailyforecast.dr.AppComponent;
import com.test.dailyforecast.dr.DaggerAppComponent;
import com.test.dailyforecast.dr.RetrofitInstance;

public class App extends Application {
    private static final String TAG = "App";
    private static App mContext;
    public static AppComponent sAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        buildAppComponents();
    }
    private void buildAppComponents() {
        sAppComponent = DaggerAppComponent.builder()
                .retrofitInstance(new RetrofitInstance())
                .build();
    }

    public static Context getContext() {
        return mContext;
    }
}
