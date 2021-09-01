package com.test.dailyforecast.dr;

import static com.test.dailyforecast.utils.Constants.BASE_URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.dailyforecast.utils.BuildConfigHelper;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {DataModule.class})
public class RetrofitInstance {

    @Inject
    public RetrofitInstance() {

    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(GsonConverterFactory converterFactory, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideConvertFactory() {
        Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .create();

        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = BuildConfigHelper.isDebugEnabled() ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC;
        interceptor.setLevel(level);

        return interceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        return client;
    }
}
