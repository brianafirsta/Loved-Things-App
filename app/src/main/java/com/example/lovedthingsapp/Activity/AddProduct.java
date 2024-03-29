package com.example.lovedthingsapp.Activity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    private ImageView fotoView;
    private Uri ImageUri;
    private Bitmap bitmap;
    private FirebaseFirestore firestore;
    private StorageReference mStorageRef;
    private String currentUserId;
    private String photoUrl;
    private String kategoriProduk;
    private String docId;

    EditText nama, ukuran, harga, deskripsi;

    ArrayList<String> arrayListCategory;
    ArrayAdapter<String> arrayAdapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        MaterialCardView pickPhoto = findViewById(R.id.pickImage);
        nama = findViewById(R.id.nama);
        ukuran = findViewById(R.id.ukuran);
        harga = findViewById(R.id.harga);
        deskripsi = findViewById(R.id.deskripsi);
        fotoView = findViewById(R.id.foto);
        Button addProductButton = findViewById(R.id.btn_simpan);

        firestore = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser().getUid();

        pickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageFromGallery();
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });

        arrayListCategory = new ArrayList<>();
        arrayListCategory.add("Atasan Wanita");
        arrayListCategory.add("Bawahan Wanita");
        arrayListCategory.add("Sepatu/Sandal Wanita");
        arrayListCategory.add("Tas Wanita");
        arrayListCategory.add("Aksesoris Wanita");
        arrayListCategory.add("Atasan Pria");
        arrayListCategory.add("Bawahan Pria");
        arrayListCategory.add("Sepatu/Sandal Pria");
        arrayListCategory.add("Tas Pria");
        arrayListCategory.add("Aksesoris Pria");

        arrayAdapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, arrayListCategory);

        Spinner category = findViewById(R.id.spinnerKategori);
        category.setAdapter(arrayAdapterCategory);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kategoriProduk = arrayListCategory.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void PickImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        ImageUri = data.getData();

                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                    getContentResolver(),
                                    ImageUri
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (ImageUri != null) {
                            fotoView.setImageBitmap(bitmap);
                        }
                    }
                }
            });

    private void UploadImage() {
        if (ImageUri != null) {
            final StorageReference myRef = mStorageRef.child("photo/" + ImageUri.getLastPathSegment());
            myRef.putFile(ImageUri)
                    .addOnSuccessListener(taskSnapshot -> myRef.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                if (uri != null) {
                                    photoUrl = uri.toString();
                                    addProduct();
                                }
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(AddProduct.this, "Gagal mengunggah gambar: " + e.getMessage(),
                                            Toast.LENGTH_SHORT).show()))
                    .addOnFailureListener(e ->
                            Toast.makeText(AddProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private void showNotification() {
        int notificationId = 1;
        String channelId = "my_channel_01";
        CharSequence channelName = "My Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Data Tersimpan")
                .setContentText("Produk anda berhasil ditambahkan")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription("Deskripsi Channel");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notificationId, builder.build());
    }

    public void addProduct() {
        String deskripsiproduk = deskripsi.getText().toString();
        String namaproduk = nama.getText().toString();
        String ukuranproduk = ukuran.getText().toString();
        String hargaproduk = harga.getText().toString();

        if (TextUtils.isEmpty(deskripsiproduk) || TextUtils.isEmpty(namaproduk) ||
                TextUtils.isEmpty(kategoriProduk) || TextUtils.isEmpty(ukuranproduk) ||
                TextUtils.isEmpty(hargaproduk) || TextUtils.isEmpty(photoUrl)) {
            Toast.makeText(this, "Harap lengkapi semua data produk", Toast.LENGTH_SHORT).show();
            return;
        }

        DocumentReference documentReference = firestore.collection("Product").document();
        Product product = new Product(namaproduk, deskripsiproduk, kategoriProduk,
                ukuranproduk, hargaproduk, photoUrl, documentReference.getId(), currentUserId);

        documentReference.set(product, SetOptions.merge())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        docId = documentReference.getId();
                        documentReference.set(product, SetOptions.merge())
                                .addOnCompleteListener(innerTask -> {
                                    if (innerTask.isSuccessful()) {
                                        showNotification();
                                    }
                                })
                                .addOnFailureListener(e ->
                                        Toast.makeText(AddProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(AddProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show());

        startActivity(new Intent(AddProduct.this, Sell.class));
    }
}
