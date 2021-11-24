package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ICU extends AppCompatActivity {
     private RecyclerView  recyclerView;
   ICUAdapter icuAdapter;
   DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icu);
        this.setTitle("ICU Service");
        databaseReference= FirebaseDatabase.getInstance().getReference("Hospital");
        recyclerView=findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Hospital> options
                = new FirebaseRecyclerOptions.Builder<Hospital>()
                .setQuery(databaseReference, Hospital.class)

                .build();

       icuAdapter = new ICUAdapter(options);

        recyclerView.setAdapter(icuAdapter);



    }
    @Override protected void onStart()
    {
        super.onStart();
        icuAdapter.startListening();
    }


    @Override protected void onStop()
    {
        super.onStop();
        icuAdapter.stopListening();
    }


}