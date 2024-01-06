package com.example.lovedthingsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lovedthingsapp.Model.BawahanPriaModel;
import com.example.lovedthingsapp.Model.BawahanWanitaModel;
import com.example.lovedthingsapp.Model.SepatuPriaModel;
import com.example.lovedthingsapp.R;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView nama,ukuran,harga,desc;
    Button addToCart, buyNow;

    //Bawahan Wanita
    BawahanWanitaModel bawahanWanitaModel = null;

    //Bawahan Pria
    BawahanPriaModel bawahanPriaModel = null;

    //Sepatu Pria
    SepatuPriaModel sepatuPriaModel = null;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        firestore = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof BawahanWanitaModel) {
            bawahanWanitaModel = (BawahanWanitaModel) obj;
        } else if (obj instanceof BawahanPriaModel) {
            bawahanPriaModel = (BawahanPriaModel) obj;
        } else if (obj instanceof SepatuPriaModel) {
            sepatuPriaModel = (SepatuPriaModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);
        nama = findViewById(R.id.detailed_nama);
        ukuran = findViewById(R.id.detailed_ukuran);
        harga = findViewById(R.id.detailed_harga);
        desc = findViewById(R.id.detailed_desc);

        addToCart = findViewById(R.id.btn_addtocart);
        buyNow = findViewById(R.id.btn_buynow);

        //Bawahan Wanita
        if (bawahanWanitaModel != null){
            Glide.with(getApplicationContext()).load(bawahanWanitaModel.getImg_url()).into(detailedImg);
            nama.setText(bawahanWanitaModel.getNama());
            ukuran.setText(bawahanWanitaModel.getUkuran());
            harga.setText(String.valueOf(bawahanWanitaModel.getHarga()));
            desc.setText(bawahanWanitaModel.getDesc());
        }
        //Bawahan Pria
        else if (bawahanPriaModel != null){
            Glide.with(getApplicationContext()).load(bawahanPriaModel.getImg_url()).into(detailedImg);
            nama.setText(bawahanPriaModel.getNama());
            ukuran.setText(bawahanPriaModel.getUkuran());
            harga.setText(String.valueOf(bawahanPriaModel.getHarga()));
            desc.setText(bawahanPriaModel.getDesc());
        }
        //Sepatu Pria
        else if (sepatuPriaModel != null){
            Glide.with(getApplicationContext()).load(sepatuPriaModel.getImg_url()).into(detailedImg);
            nama.setText(sepatuPriaModel.getNama());
            ukuran.setText(sepatuPriaModel.getUkuran());
            harga.setText(String.valueOf(sepatuPriaModel.getHarga()));
            desc.setText(sepatuPriaModel.getDesc());
        }
    }
}