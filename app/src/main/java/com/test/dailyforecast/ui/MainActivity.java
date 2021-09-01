package com.test.dailyforecast.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.test.dailyforecast.R;
import com.test.dailyforecast.databinding.ActivityMainBinding;
import com.test.dailyforecast.services.interfaces.Listeners;
import com.test.dailyforecast.utils.App;
import com.test.dailyforecast.viewModel.WeatherViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Listeners {

    ActivityMainBinding binding;
    @Inject
    WeatherViewModel viewModel;
    FragmentTransaction dataFg, errorFg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        App.sAppComponent.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));


        binding.searchImg.setOnClickListener(view -> {
            if (binding.cityNameEd.getText().length() > 0) {
                viewModel.listData(binding.cityNameEd.getText().toString());
            } else {
                binding.cityNameEd.setError("Enter City Name");
            }
        });

        viewModel.mutableLiveData.observe(this, responseResult -> {
            if (responseResult != null) {
                if (responseResult.getCod().equals("200")) {
                    //Ok

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, WeatherListData.newInstance(responseResult)).addToBackStack(WeatherListData.class.getName()).commit();
                } else {
                    //Handle Error
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, FragmentError.newInstance(responseResult)).addToBackStack(FragmentError.class.getName()).commit();

                }
            } else {
                viewModel.mutableErrorLiveData.observe(this, s -> {
                    if (s != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, FragmentError.newInstance("No Data Found")).addToBackStack(FragmentError.class.getName()).commit();
                    }
                });

            }
        });
    }

    @Override
    public void refresh() {
        if (binding.cityNameEd.getText().length() > 0) {
            viewModel.listData(binding.cityNameEd.getText().toString());
        } else {
            binding.cityNameEd.setError("Enter City Name");
        }
    }
}