package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lovedthingsapp.Adaptor.AksesorisPriaAdaptor;
import com.example.lovedthingsapp.Adaptor.BawahanPriaAdaptor;
import com.example.lovedthingsapp.Model.AksesorisPriaModel;
import com.example.lovedthingsapp.Model.BawahanPriaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AksesorisPriaActivity extends AppCompatActivity {

    RecyclerView aksesorisPriaRecyclerview;

    AksesorisPriaAdaptor aksesorisPriaAdaptor;
    List<AksesorisPriaModel> aksesorisPriaModelList;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksesoris_pria);

        aksesorisPriaRecyclerview = findViewById(R.id.recyclerViewAksesorisPria);

        db = FirebaseFirestore.getInstance();

        //Aksesoris Pria
        aksesorisPriaRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        aksesorisPriaModelList = new ArrayList<>();
        aksesorisPriaAdaptor = new AksesorisPriaAdaptor(this, aksesorisPriaModelList);
        aksesorisPriaRecyclerview.setAdapter(aksesorisPriaAdaptor);

        db.collection("Aksesoris Pria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AksesorisPriaModel aksesorisPriaModel = document.toObject(AksesorisPriaModel.class);
                                aksesorisPriaModelList.add(aksesorisPriaModel);
                                aksesorisPriaAdaptor.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}