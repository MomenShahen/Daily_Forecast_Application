package com.test.dailyforecast.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.dailyforecast.model.ResponseResult;
import com.test.dailyforecast.services.repositories.DataRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class WeatherViewModel extends ViewModel {


    private DataRepository dataRepository;

    public MutableLiveData<ResponseResult> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> mutableErrorLiveData = new MutableLiveData<>();

    @Inject
    public WeatherViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void listData(String city) {
        dataRepository.getListData(city).observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataItems -> {
                    //Save in live data
                    if (dataItems != null ) {
                        mutableLiveData.setValue(dataItems);
                        mutableErrorLiveData.setValue(null);
                    }
                }, throwable -> {
                    Log.e("Interview App", "Throwable " + throwable.getMessage());
                    mutableLiveData.setValue(null);
                    mutableErrorLiveData.setValue(throwable.getMessage());
                });
    }
}