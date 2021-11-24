package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Authority extends AppCompatActivity implements View.OnClickListener {
    private EditText hospitalName, email, password, ID, IcuCost, IcuAvailable, surgeryCost, ecgCost, XrayCost;
    private Button submit;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth2;
    private int ok1 = 0, ok2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        this.setTitle("Authority Registration");
        mAuth2 = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Hospital");
        hospitalName = (EditText) findViewById(R.id.hospitalnameId);
        email = (EditText) findViewById(R.id.authEmailId);
        password = (EditText) findViewById(R.id.authpasswordId);
        ID = (EditText) findViewById(R.id.hospitalId);
        IcuAvailable = (EditText) findViewById(R.id.availableIcuId);
        IcuCost = (EditText) findViewById(R.id.icucostId);
        surgeryCost = (EditText) findViewById(R.id.surgerycostId);
        ecgCost = (EditText) findViewById(R.id.ecgcostId);
        XrayCost = (EditText) findViewById(R.id.xraycostId);
        submit = (Button) findViewById(R.id.SubmitId);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SubmitId) {
            String hosname = hospitalName.getText().toString().trim();
            String Email = email.getText().toString().trim();
            String Password = password.getText().toString().trim();
            String Id = ID.getText().toString().trim();
            String Icu = IcuAvailable.getText().toString().trim();
            String icuCost = IcuCost.getText().toString().trim();
            String SurgeryCost = surgeryCost.getText().toString().trim();
            String EcgCost = ecgCost.getText().toString().trim();
            String xrayCost = XrayCost.getText().toString().trim();
            String rating ="0";
            String userRated="0";
            String totalRating="0";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.hasChild(Id)) {

                        // Toast.makeText(getApplicationContext(), "log in Successful", Toast.LENGTH_SHORT).show();



                        if (Email.isEmpty()) {
                            email.setError("Enter an email address !");
                            email.requestFocus();
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                            email.setError("Enter a valid email address !");
                            email.requestFocus();
                        }
                        if (Password.isEmpty()) {
                            password.setError("Please enter a password!");
                            email.requestFocus();
                        } else if (Password.length() < 8) {
                            password.setError("Password length should be minimum 8!");
                            email.requestFocus();
                        }

                        mAuth2.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Hospital hospital = new Hospital(hosname, Id, Icu, icuCost, SurgeryCost, EcgCost, xrayCost, rating,userRated,totalRating);
                                    databaseReference.child(Id).setValue(hospital);
                                    Toast.makeText(Authority.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Authority.this, Authority_logIn.class);
                                    startActivity(intent);



                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getApplicationContext(), "Already Registered with this ", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error :" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    }
                     else
                    {
                        Toast.makeText(getApplicationContext(), "ID already Exist", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });



        }
    }
}