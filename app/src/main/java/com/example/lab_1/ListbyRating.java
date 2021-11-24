package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListbyRating extends AppCompatActivity {
  private ListView listView;
  private CustomAdapter customAdapter;
  private List<Hospital> hospitalList;
  DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listby_rating);
        setTitle("Rating List(Descending)");
        databaseReference= FirebaseDatabase.getInstance().getReference("Hospital");
        listView=findViewById(R.id.listbyratingId);
        hospitalList=new ArrayList<>();
        loadData();

        customAdapter=new CustomAdapter(ListbyRating.this,hospitalList);


    }

    private void loadData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                hospitalList.clear();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    Hospital hospital=ds.getValue(Hospital.class);
                    hospitalList.add(hospital);
                }
                 Collections.sort(hospitalList, new Comparator<Hospital>() {
            @Override
            public int compare(Hospital o1, Hospital o2) {
                return o2.getRating().compareTo(o1.getRating());
            }
        });
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}