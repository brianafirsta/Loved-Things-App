package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lovedthingsapp.Adaptor.BawahanPriaAdaptor;
import com.example.lovedthingsapp.Adaptor.BawahanWanitaAdaptor;
import com.example.lovedthingsapp.Model.BawahanPriaModel;
import com.example.lovedthingsapp.Model.BawahanWanitaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BawahanPriaActivity extends AppCompatActivity {

    RecyclerView bawahanPriaRecyclerview;

    BawahanPriaAdaptor bawahanPriaAdaptor;
    List<BawahanPriaModel> bawahanPriaModelList;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawahan_pria);

        bawahanPriaRecyclerview = findViewById(R.id.recyclerViewBawahanPria);

        db = FirebaseFirestore.getInstance();

        //Bawahan Pria
        bawahanPriaRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        bawahanPriaModelList = new ArrayList<>();
        bawahanPriaAdaptor = new BawahanPriaAdaptor(this, bawahanPriaModelList);
        bawahanPriaRecyclerview.setAdapter(bawahanPriaAdaptor);

        db.collection("Bawahan Pria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BawahanPriaModel bawahanPriaModel = document.toObject(BawahanPriaModel.class);
                                bawahanPriaModelList.add(bawahanPriaModel);
                                bawahanPriaAdaptor.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}