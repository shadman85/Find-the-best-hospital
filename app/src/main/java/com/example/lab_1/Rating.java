package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Rating extends AppCompatActivity implements View.OnClickListener {
        private EditText id,userRate;
        private Button ok,ratingList;
        DatabaseReference databaseReference;
    double rate,userNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        this.setTitle("Rating");
        id=(EditText)findViewById(R.id.userRateId);
        userRate=(EditText)findViewById(R.id.userGivenRateId);
        ratingList=(Button)findViewById(R.id.ratingListId);
        ratingList.setOnClickListener(this);
        ok=(Button)findViewById(R.id.userclickokId);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.userclickokId)
        {
            String ID=id.getText().toString().trim();
            String rt=userRate.getText().toString().trim();
            double givenrate=Double.parseDouble(rt);


            databaseReference= FirebaseDatabase.getInstance().getReference("Hospital");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(ID))
                    {
                        if(givenrate>=0 && givenrate<=5)
                        {
                            databaseReference.child(ID).child("totalRating").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    String tmp=snapshot.getValue(String.class);
                                    rate=Double.parseDouble(tmp);

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });
                            databaseReference.child(ID).child("userRated").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    String tmp=snapshot.getValue(String.class);
                                    userNumber=Double.parseDouble(tmp);

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });
                            userNumber=userNumber+1;
                            rate=rate+givenrate;
                            double avg=rate/userNumber;
                            //double roundOff = Math.round(avg*100)/100;
                            String newRating=Double.toString(avg);
                            String newTotalRating=Double.toString(rate);
                            String newUserRated=Double.toString(userNumber);
                            databaseReference.child(ID).child("totalRating").setValue(newTotalRating);
                            databaseReference.child(ID).child("rating").setValue(newRating);
                            databaseReference.child(ID).child("userRated").setValue(newUserRated);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please Enter any number from 0 to 5",Toast.LENGTH_SHORT).show();
                        }

                        id.getText().clear();
                        userRate.getText().clear();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Valid ID",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });





        }
        if(v.getId()==R.id.ratingListId)
        {
            Intent intent = new Intent(getApplicationContext(), ListbyRating.class);
            startActivity(intent);
        }

    }
}