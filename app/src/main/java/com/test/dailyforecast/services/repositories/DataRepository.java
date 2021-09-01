package com.test.dailyforecast.services.repositories;

import com.test.dailyforecast.model.ResponseResult;
import com.test.dailyforecast.services.interfaces.MyServicesInterface;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class DataRepository {

    private MyServicesInterface servicesInterface;

    @Inject
    public DataRepository(MyServicesInterface myServicesInterface){
        this.servicesInterface = myServicesInterface;
    }

    public Observable<ResponseResult> getListData(final String city) {
        return servicesInterface.getListData(city);
    }


}
