package com.test.dailyforecast.ui;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.test.dailyforecast.R;
import com.test.dailyforecast.databinding.WeatherDataItemBinding;
import com.test.dailyforecast.utils.StringUtil;

import java.util.List;


public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.Vholder> {
    List<com.test.dailyforecast.model.List> dataItems;
    Context context;

    public DataListAdapter(List<com.test.dailyforecast.model.List> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherDataItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.weather_data_item, parent, false);
        return new Vholder(itemBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {
        com.test.dailyforecast.model.List item = dataItems.get(position);
        int d = (int)(item.getMain().getTemp() - 273.15);
        String[] dateTime = StringUtil.displayTime(item.getDt_txt());
        ((Vholder) holder).binding.dateTxt.setText(dateTime[0]);
        ((Vholder) holder).binding.timeTxt.setText(dateTime[1]);
        ((Vholder) holder).binding.degreeTxt.setText(String.format("%d°", d));
        ((Vholder) holder).binding.descTxt.setText(String.valueOf(item.getWeather().get(0).getDescription()));
        ((Vholder) holder).binding.mainTxt.setText(String.valueOf(item.getWeather().get(0).getMain()));


    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }


    public class Vholder extends RecyclerView.ViewHolder {
        WeatherDataItemBinding binding;

        public Vholder(@NonNull WeatherDataItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

}
