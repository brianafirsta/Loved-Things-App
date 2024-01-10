package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lovedthingsapp.Adaptor.SepatuPriaAdaptor;
import com.example.lovedthingsapp.Model.SepatuPriaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SepatuPriaActivity extends AppCompatActivity {

    RecyclerView sepatuPriaRecyclerview;

    SepatuPriaAdaptor sepatuPriaAdaptor;
    List<SepatuPriaModel> sepatuPriaModelList;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepatu_pria);

        sepatuPriaRecyclerview = findViewById(R.id.recyclerViewSepatuPria);

        db = FirebaseFirestore.getInstance();

        //Sepatu Pria
        sepatuPriaRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        sepatuPriaModelList = new ArrayList<>();
        sepatuPriaAdaptor = new SepatuPriaAdaptor(this, sepatuPriaModelList);
        sepatuPriaRecyclerview.setAdapter(sepatuPriaAdaptor);

        db.collection("Sepatu Pria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SepatuPriaModel sepatuPriaModel = document.toObject(SepatuPriaModel.class);
                                sepatuPriaModelList.add(sepatuPriaModel);
                                sepatuPriaAdaptor.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}