package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedRating extends AppCompatActivity {
        private RecyclerView recyclerView;
        private List<Hospital> hospitalList;
        AdapterRating adapterRating;
        DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_rating);
        recyclerView=findViewById(R.id.ratingRecyclerId);
        hospitalList=new ArrayList<Hospital>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Hospital");

      /* Collections.sort(hospitalList, new Comparator<Hospital>() {
            @Override
            public int compare(Hospital o1, Hospital o2) {
                return o1.getRating().compareTo(o2.getRating());
            }
        });*/
        for(int i=0; i<hospitalList.size();i++)
        {
            for(int j=0;j<hospitalList.size();j++)
            {
                Hospital v1,v2;
                v1= hospitalList.get(i);
                v2=hospitalList.get(j);
                double b1,b2;
                b1=Double.parseDouble(v1.getRating());
                b2=Double.parseDouble(v2.getRating());
                if(b1<b2)
                {
                    Collections.swap(hospitalList, i, j);
                }
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterRating=new AdapterRating(hospitalList,this);
        recyclerView.setAdapter(adapterRating);

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    Hospital hospital=ds.getValue(Hospital.class);
                    hospitalList.add(hospital);
                }
                recyclerView.setAdapter(adapterRating);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}