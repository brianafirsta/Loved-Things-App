package com.example.lovedthingsapp.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lovedthingsapp.Model.CartModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    Context context;
    List<CartModel> list;

    public CartAdaptor(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img_url);

        holder.harga.setText("Rp " + list.get(position).getHargaProduk());
        holder.nama.setText(list.get(position).getNamaProduk());
        holder.ukuran.setText(list.get(position).getUkuranProduk());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView harga, nama, ukuran;
        ImageView img_url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            harga = itemView.findViewById(R.id.cart_harga);
            nama = itemView.findViewById(R.id.cart_nama);
            ukuran = itemView.findViewById(R.id.cart_ukuran);
            img_url = itemView.findViewById(R.id.cart_image);
        }
    }
}

