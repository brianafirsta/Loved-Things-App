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
import com.example.lovedthingsapp.Model.TasWanitaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class TasWanitaAdaptor extends RecyclerView.Adapter<TasWanitaAdaptor.ViewHolder> {

    private Context context;
    private List<TasWanitaModel> list;

    public TasWanitaAdaptor(Context context, List<TasWanitaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TasWanitaAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasWanitaAdaptor.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bawahanwanita,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TasWanitaAdaptor.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.watasImg);
        holder.watasName.setText(list.get(position).getNama());
        holder.watasSize.setText(list.get(position).getUkuran());
        holder.watasPrice.setText("Rp " +list.get(position).getHarga());

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

        ImageView watasImg;
        TextView watasName;
        TextView watasSize;
        TextView watasPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            watasImg = itemView.findViewById(R.id.wanitaPic);
            watasName = itemView.findViewById(R.id.wanitaName);
            watasSize = itemView.findViewById(R.id.wanitaSize);
            watasPrice = itemView.findViewById(R.id.wanitaPrice);
        }
    }
}
