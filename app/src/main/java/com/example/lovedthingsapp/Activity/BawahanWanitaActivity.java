package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lovedthingsapp.Adaptor.BawahanWanitaAdaptor;
import com.example.lovedthingsapp.Model.BawahanWanitaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BawahanWanitaActivity extends AppCompatActivity {
    RecyclerView bawahanWanitaRecyclerview;

    BawahanWanitaAdaptor bawahanWanitaAdaptor;
    List<BawahanWanitaModel> bawahanWanitaModelList;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawahan_wanita);

        bawahanWanitaRecyclerview = findViewById(R.id.recyclerViewBawahanWanita);

        db = FirebaseFirestore.getInstance();

        //Bawahan Wanita
        bawahanWanitaRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        bawahanWanitaModelList = new ArrayList<>();
        bawahanWanitaAdaptor = new BawahanWanitaAdaptor(this, bawahanWanitaModelList);
        bawahanWanitaRecyclerview.setAdapter(bawahanWanitaAdaptor);

        db.collection("Bawahan Wanita")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BawahanWanitaModel bawahanWanitaModel = document.toObject(BawahanWanitaModel.class);
                                bawahanWanitaModelList.add(bawahanWanitaModel);
                                bawahanWanitaAdaptor.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}