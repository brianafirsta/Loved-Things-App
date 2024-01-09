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
import com.example.lovedthingsapp.Model.BawahanWanitaModel;
import com.example.lovedthingsapp.Model.WanitaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class BawahanWanitaAdaptor extends RecyclerView.Adapter<BawahanWanitaAdaptor.ViewHolder> {

    private Context context;
    private List<BawahanWanitaModel> list;

    public BawahanWanitaAdaptor(Context context, List<BawahanWanitaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bawahanwanita,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.wabawahanImg);
        holder.wabawahanName.setText(list.get(position).getNama());
        holder.wabawahanSize.setText(list.get(position).getUkuran());
        holder.wabawahanPrice.setText("Rp " +list.get(position).getHarga());

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

        ImageView wabawahanImg;
        TextView wabawahanName;
        TextView wabawahanSize;
        TextView wabawahanPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wabawahanImg = itemView.findViewById(R.id.wanitaPic);
            wabawahanName = itemView.findViewById(R.id.wanitaName);
            wabawahanSize = itemView.findViewById(R.id.wanitaSize);
            wabawahanPrice = itemView.findViewById(R.id.wanitaPrice);
        }
    }
}
