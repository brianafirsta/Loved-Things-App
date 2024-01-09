package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lovedthingsapp.Model.AksesorisPriaModel;
import com.example.lovedthingsapp.Model.BawahanPriaModel;
import com.example.lovedthingsapp.Model.BawahanWanitaModel;
import com.example.lovedthingsapp.Model.SepatuPriaModel;
import com.example.lovedthingsapp.Model.TasWanitaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView img_url;
    TextView nama,ukuran,harga,desc;
    Button addToCart, buyNow;

    //Bawahan Wanita
    BawahanWanitaModel bawahanWanitaModel = null;

    //Bawahan Pria
    BawahanPriaModel bawahanPriaModel = null;

    //Sepatu Pria
    SepatuPriaModel sepatuPriaModel = null;

    //Tas Wanita
    TasWanitaModel tasWanitaModel = null;

    //Aksesoris Pria
    AksesorisPriaModel aksesorisPriaModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof BawahanWanitaModel) {
            bawahanWanitaModel = (BawahanWanitaModel) obj;
        }
        if (obj instanceof BawahanPriaModel) {
            bawahanPriaModel = (BawahanPriaModel) obj;
        }
        if (obj instanceof SepatuPriaModel) {
            sepatuPriaModel = (SepatuPriaModel) obj;
        }
        if (obj instanceof TasWanitaModel) {
            tasWanitaModel = (TasWanitaModel) obj;
        }
        if (obj instanceof AksesorisPriaModel) {
            aksesorisPriaModel = (AksesorisPriaModel) obj;
        }


        img_url = findViewById(R.id.detailed_img);
        nama = findViewById(R.id.detailed_nama);
        ukuran = findViewById(R.id.detailed_ukuran);
        harga = findViewById(R.id.detailed_harga);
        desc = findViewById(R.id.detailed_desc);

        addToCart = findViewById(R.id.btn_addtocart);
        buyNow = findViewById(R.id.btn_buynow);

        //Bawahan Wanita
        if (bawahanWanitaModel != null){
            Glide.with(getApplicationContext()).load(bawahanWanitaModel.getImg_url()).into(img_url);
            nama.setText(bawahanWanitaModel.getNama());
            ukuran.setText(bawahanWanitaModel.getUkuran());
            harga.setText(String.valueOf(bawahanWanitaModel.getHarga()));
            desc.setText(bawahanWanitaModel.getDesc());

            img_url.setTag(bawahanWanitaModel.getImg_url());
        }
        //Bawahan Pria
        if (bawahanPriaModel != null){
            Glide.with(getApplicationContext()).load(bawahanPriaModel.getImg_url()).into(img_url);
            nama.setText(bawahanPriaModel.getNama());
            ukuran.setText(bawahanPriaModel.getUkuran());
            harga.setText(String.valueOf(bawahanPriaModel.getHarga()));
            desc.setText(bawahanPriaModel.getDesc());

            img_url.setTag(bawahanPriaModel.getImg_url());
        }
        //Sepatu Pria
        if (sepatuPriaModel != null){
            Glide.with(getApplicationContext()).load(sepatuPriaModel.getImg_url()).into(img_url);
            nama.setText(sepatuPriaModel.getNama());
            ukuran.setText(sepatuPriaModel.getUkuran());
            harga.setText(String.valueOf(sepatuPriaModel.getHarga()));
            desc.setText(sepatuPriaModel.getDesc());

            img_url.setTag(sepatuPriaModel.getImg_url());
        }
        //Tas Wanita
        if (tasWanitaModel != null){
            Glide.with(getApplicationContext()).load(tasWanitaModel.getImg_url()).into(img_url);
            nama.setText(tasWanitaModel.getNama());
            ukuran.setText(tasWanitaModel.getUkuran());
            harga.setText(String.valueOf(tasWanitaModel.getHarga()));
            desc.setText(tasWanitaModel.getDesc());

            img_url.setTag(tasWanitaModel.getImg_url());
        }
        //Aksesoris Pria
        if (aksesorisPriaModel != null){
            Glide.with(getApplicationContext()).load(aksesorisPriaModel.getImg_url()).into(img_url);
            nama.setText(aksesorisPriaModel.getNama());
            ukuran.setText(aksesorisPriaModel.getUkuran());
            harga.setText(String.valueOf(aksesorisPriaModel.getHarga()));
            desc.setText(aksesorisPriaModel.getDesc());

            img_url.setTag(aksesorisPriaModel.getImg_url());
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }

    private void addToCart() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("namaProduk", nama.getText().toString());
        cartMap.put("ukuranProduk", ukuran.getText().toString());
        cartMap.put("hargaProduk", harga.getText().toString());

        cartMap.put("img_url", img_url.getTag());

        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Ditambahkan Ke Keranjang", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}