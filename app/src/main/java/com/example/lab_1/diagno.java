package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class diagno extends AppCompatActivity {
    private RecyclerView recyclerView;
    DgnAdapter dgnAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagno);
        this.setTitle("Diagnosis Services");
        databaseReference= FirebaseDatabase.getInstance().getReference("Hospital");
        recyclerView=findViewById(R.id.recyclerdgnId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Hospital> options
                = new FirebaseRecyclerOptions.Builder<Hospital>()
                .setQuery(databaseReference, Hospital.class)
                .build();

        dgnAdapter = new DgnAdapter(options);

        recyclerView.setAdapter(dgnAdapter);
    }
    @Override protected void onStart()
    {
        super.onStart();
        dgnAdapter.startListening();
    }


    @Override protected void onStop()
    {
        super.onStop();
        dgnAdapter.stopListening();
    }


}