package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lovedthingsapp.Adaptor.BawahanWanitaAdaptor;
import com.example.lovedthingsapp.Adaptor.TasWanitaAdaptor;
import com.example.lovedthingsapp.Model.BawahanWanitaModel;
import com.example.lovedthingsapp.Model.TasWanitaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TasWanitaActivity extends AppCompatActivity {

    RecyclerView tasWanitaRecyclerview;

    TasWanitaAdaptor tasWanitaAdaptor;
    List<TasWanitaModel> tasWanitaModelList;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tas_wanita);

        tasWanitaRecyclerview = findViewById(R.id.recyclerViewTasWanita);

        db = FirebaseFirestore.getInstance();

        //Tas Wanita
        tasWanitaRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        tasWanitaModelList = new ArrayList<>();
        tasWanitaAdaptor = new TasWanitaAdaptor(this, tasWanitaModelList);
        tasWanitaRecyclerview.setAdapter(tasWanitaAdaptor);

        db.collection("Tas Wanita")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TasWanitaModel tasWanitaModel = document.toObject(TasWanitaModel.class);
                                tasWanitaModelList.add(tasWanitaModel);
                                tasWanitaAdaptor.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}