package com.example.user.yukshalat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.yukshalat.R;
import com.example.user.yukshalat.responseApi.ItemsItem;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context c;
    private List<ItemsItem> mDataset;

    private String nameSholat[] = {"Subuh", "Syuruq", "Dzuhur", "Ashar", "Maghrib", "Isya"};

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameSholat, tvTimeSholat;

        ViewHolder(View v) {
            super(v);
            tvNameSholat = v.findViewById(R.id.tv_name);
            tvTimeSholat = v.findViewById(R.id.tv_timer);

        }
    }


    public MyAdapter(Context cc, List<ItemsItem> dataSchedule) {
        this.c = cc;
        mDataset = dataSchedule;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String[] timeSholat = new String[]{
                mDataset.get(getItemCount()).getFajr(),
                mDataset.get(getItemCount()).getShurooq(),
                mDataset.get(getItemCount()).getDhuhr(),
                mDataset.get(getItemCount()).getAsr(),
                mDataset.get(getItemCount()).getMaghrib(),
                mDataset.get(getItemCount()).getIsha()
        };

        holder.tvNameSholat.setText(nameSholat[position]);
        holder.tvTimeSholat.setText(timeSholat[position]);

    }


    @Override
    public int getItemCount() {
        return nameSholat.length;
    }
}