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
import com.example.lovedthingsapp.Model.AksesorisPriaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class AksesorisPriaAdaptor extends RecyclerView.Adapter<AksesorisPriaAdaptor.ViewHolder> {

    private Context context;
    private List<AksesorisPriaModel> list;

    public AksesorisPriaAdaptor(Context context, List<AksesorisPriaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AksesorisPriaAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AksesorisPriaAdaptor.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bawahanpria,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AksesorisPriaAdaptor.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.praksesorisImg);
        holder.praksesorisName.setText(list.get(position).getNama());
        holder.praksesorisSize.setText(list.get(position).getUkuran());
        holder.praksesorisPrice.setText("Rp " +list.get(position).getHarga());

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

        ImageView praksesorisImg;
        TextView praksesorisName;
        TextView praksesorisSize;
        TextView praksesorisPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            praksesorisImg = itemView.findViewById(R.id.priaPic);
            praksesorisName = itemView.findViewById(R.id.priaName);
            praksesorisSize = itemView.findViewById(R.id.priaSize);
            praksesorisPrice = itemView.findViewById(R.id.priaPrice);
        }
    }
}
