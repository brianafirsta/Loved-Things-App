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
import com.example.lovedthingsapp.R;

import java.util.List;

public class BawahanPriaAdaptor extends RecyclerView.Adapter<BawahanPriaAdaptor.ViewHolder> {

    private Context context;
    private List<BawahanPriaModel> list;

    public BawahanPriaAdaptor(Context context, List<BawahanPriaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BawahanPriaAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bawahanpria,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BawahanPriaAdaptor.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.prbawahanImg);
        holder.prbawahanName.setText(list.get(position).getNama());
        holder.prbawahanSize.setText(list.get(position).getUkuran());
        holder.prbawahanPrice.setText("Rp " +list.get(position).getHarga());

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

        ImageView prbawahanImg;
        TextView prbawahanName;
        TextView prbawahanSize;
        TextView prbawahanPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prbawahanImg = itemView.findViewById(R.id.priaPic);
            prbawahanName = itemView.findViewById(R.id.priaName);
            prbawahanSize = itemView.findViewById(R.id.priaSize);
            prbawahanPrice = itemView.findViewById(R.id.priaPrice);
        }
    }
}
