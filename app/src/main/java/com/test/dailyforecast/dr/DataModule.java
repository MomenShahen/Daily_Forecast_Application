package com.test.dailyforecast.dr;

import com.test.dailyforecast.services.interfaces.MyServicesInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DataModule {
    @Provides
    @Singleton
    public MyServicesInterface provideApiService(Retrofit retrofit) {
        return retrofit.create(MyServicesInterface.class);
    }

//    @Provides
//    @Singleton
//    public DataRepository provideRepository(MyServicesInterface myServicesInterface) {
//        return new DataRepository(myServicesInterface);
//    }
}
