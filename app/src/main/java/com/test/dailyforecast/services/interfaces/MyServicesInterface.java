package com.test.dailyforecast.services.interfaces;

import com.test.dailyforecast.model.ResponseResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyServicesInterface {
    @GET("/data/2.5/forecast?appid=dbfa08f064a5d063f115c4f2e9b54596")
    Observable<ResponseResult> getListData(@Query("q") String cityname);
}
