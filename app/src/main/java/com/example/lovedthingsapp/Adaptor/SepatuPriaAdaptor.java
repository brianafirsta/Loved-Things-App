package com.example.lovedthingsapp.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lovedthingsapp.Activity.DetailedActivity;
import com.example.lovedthingsapp.Model.BawahanPriaModel;
import com.example.lovedthingsapp.Model.SepatuPriaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class SepatuPriaAdaptor extends RecyclerView.Adapter<SepatuPriaAdaptor.ViewHolder> {

    private Context context;
    private List<SepatuPriaModel> list;

    public SepatuPriaAdaptor(Context context, List<SepatuPriaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SepatuPriaAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bawahanpria,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SepatuPriaAdaptor.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.prsepatuImg);
        holder.prsepatuName.setText(list.get(position).getNama());
        holder.prsepatuSize.setText(list.get(position).getUkuran());
        holder.prsepatuPrice.setText(String.valueOf(list.get(position).getHarga()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView prsepatuImg;
        TextView prsepatuName;
        TextView prsepatuSize;
        TextView prsepatuPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prsepatuImg = itemView.findViewById(R.id.priaPic);
            prsepatuName = itemView.findViewById(R.id.priaName);
            prsepatuSize = itemView.findViewById(R.id.priaSize);
            prsepatuPrice = itemView.findViewById(R.id.priaPrice);
        }
    }
}
