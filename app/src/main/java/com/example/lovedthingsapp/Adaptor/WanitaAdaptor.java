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
import com.example.lovedthingsapp.Activity.AtasanWanitaActivity;
import com.example.lovedthingsapp.Activity.BawahanWanitaActivity;
import com.example.lovedthingsapp.Activity.SepatuWanitaActivity;
import com.example.lovedthingsapp.Activity.TasWanitaActivity;
import com.example.lovedthingsapp.Model.WanitaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class WanitaAdaptor extends RecyclerView.Adapter<WanitaAdaptor.ViewHolder> {

    private Context context;
    private List<WanitaModel> list;

    public WanitaAdaptor(Context context, List<WanitaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_wanita,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.waImg);
        holder.waName.setText(list.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, BawahanWanitaActivity.class);
//                intent.putExtra("bawahan wanita",list.get(position));
//                context.startActivity(intent);
                Intent intent;

                // Menyesuaikan Intent dan data tambahan berdasarkan jenis item
                switch (list.get(position).getJenisItem()) {
                    case "Bawahan Wanita":
                        intent = new Intent(context, BawahanWanitaActivity.class);
                        break;
                    case "Atasan Wanita":
                        intent = new Intent(context, AtasanWanitaActivity.class);
                        break;
                    case "Sepatu Wanita":
                        intent = new Intent(context, SepatuWanitaActivity.class);
                        break;
                    case "Tas Wanita":
                        intent = new Intent(context, TasWanitaActivity.class);
                        break;
                    default:
                        // Tambahkan handling untuk jenis item lain jika diperlukan
                        return;
                }

                // Menambahkan data tambahan ke Intent
                intent.putExtra("jenisItem", list.get(position));

                // Memulai activity baru
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView waImg;
        TextView waName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            waImg = itemView.findViewById(R.id.wanitaPic);
            waName = itemView.findViewById(R.id.wanitaName);
        }
    }
}
