package com.test.dailyforecast.dr;


import com.test.dailyforecast.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitInstance.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
