package com.test.dailyforecast.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.dailyforecast.R;
import com.test.dailyforecast.databinding.WeatherListLayoutBinding;
import com.test.dailyforecast.model.ResponseResult;
import com.test.dailyforecast.utils.App;

public class WeatherListData extends Fragment {
    WeatherListLayoutBinding binding;
    ResponseResult result;
    RecyclerView.Adapter adapter;

    public static WeatherListData newInstance(ResponseResult responseResult){
        WeatherListData error = new WeatherListData();
        error.result = responseResult;
        return error;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_list_layout, container, false);
        binding.rec.setLayoutManager(new LinearLayoutManager(App.getContext()));
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("WeatherListData","Data has Arrived");
        adapter = new DataListAdapter(result.getList(), App.getContext());
        binding.rec.setAdapter(adapter);
    }
}
