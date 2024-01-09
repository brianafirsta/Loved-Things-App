package com.example.lovedthingsapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adaptor.CartAdaptor;
import com.example.lovedthingsapp.Model.CartModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    List<CartModel> cartModelList;
    CartAdaptor cartAdaptor;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        cartAdaptor = new CartAdaptor(this, cartModelList);
        recyclerView.setAdapter(cartAdaptor);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                CartModel cartModel = doc.toObject(CartModel.class);
                                cartModelList.add(cartModel);
                            }
                            // Update RecyclerView setelah data diambil
                            cartAdaptor.notifyDataSetChanged();
                        }
                    }
                });
    }
}
