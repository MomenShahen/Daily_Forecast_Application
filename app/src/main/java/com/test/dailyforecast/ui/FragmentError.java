package com.test.dailyforecast.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.test.dailyforecast.R;
import com.test.dailyforecast.databinding.ErrorLayoutBinding;
import com.test.dailyforecast.model.ResponseResult;
import com.test.dailyforecast.services.interfaces.Listeners;

public class FragmentError extends Fragment {
    ErrorLayoutBinding binding;
    ResponseResult result;
    String errorMsg;
    Listeners listeners;

    public static FragmentError newInstance(ResponseResult responseResult){
        FragmentError error = new FragmentError();
        error.result = responseResult;
        return error;
    }

    public static FragmentError newInstance(String errorMsg){
        FragmentError error = new FragmentError();
        error.errorMsg = errorMsg;
        return error;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Listeners) {
            //init the listener
            listeners = (Listeners) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.error_layout, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (result != null)
        binding.errorTxt.setText(result.getMessage());
        else
            binding.errorTxt.setText(errorMsg);
        binding.refreshBtn.setOnClickListener(v->{
            listeners.refresh();
        });
    }
}
