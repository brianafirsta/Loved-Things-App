package com.example.lovedthingsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.lovedthingsapp.Adaptor.PriaAdaptor;
import com.example.lovedthingsapp.Adaptor.WanitaAdaptor;
import com.example.lovedthingsapp.Domain.PriaDomain;
import com.example.lovedthingsapp.Domain.WanitaDomain;
import com.example.lovedthingsapp.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewWanita();
        recyclerViewPria();
    }

    private void recyclerViewWanita() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerViewWa);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<WanitaDomain> wanita=new ArrayList<>();
        wanita.add(new WanitaDomain("Atasan", "wa"));
        wanita.add(new WanitaDomain("Bawahan", "wb"));
        wanita.add(new WanitaDomain("Sepatu/Sandal", "ws"));

        adapter=new WanitaAdaptor(wanita);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPria() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerViewPr);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<PriaDomain> pria=new ArrayList<>();
        pria.add(new PriaDomain("Atasan", "pa"));
        pria.add(new PriaDomain("Bawahan", "pb"));
        pria.add(new PriaDomain("Sepatu/Sandal", "ps"));

        adapter=new PriaAdaptor(pria);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}