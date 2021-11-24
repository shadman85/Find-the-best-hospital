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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Authority_logIn extends AppCompatActivity implements View.OnClickListener {
    private EditText pin,username,password;
    private Button logIn;
    private TextView register;
    private FirebaseAuth mAuth2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority_log_in);
        this.setTitle("Authority Log In");
        mAuth2 = FirebaseAuth.getInstance();
         username=(EditText)findViewById(R.id.authorityUsernameId);
         password=(EditText)findViewById(R.id.authorityPasswordId);
        logIn = (Button) findViewById(R.id.logId);
        register = (TextView) findViewById(R.id.registerId);
        logIn.setOnClickListener(this);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.registerId) {

            Intent intent = new Intent(Authority_logIn.this, Authority.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.logId) {
            String Username=username.getText().toString().trim();
            String Password=password.getText().toString().trim();
            databaseReference = FirebaseDatabase.getInstance().getReference("Hospital");

            if(Username.isEmpty())
            {
                username.setError("Enter an email address !");
                username.requestFocus();
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(Username).matches())
            {
                username.setError("Enter a valid email address !");
                username.requestFocus();
            }
            if(Password.isEmpty())
            {
                password.setError("Please enter a password!");
                password.requestFocus();
            }
            else if(Password.length()<8)
            {
                password.setError("Password length should be minimum 8!");
                password.requestFocus();
            }


            mAuth2.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        username.getText().clear();
                        password.getText().clear();

                        Intent intent=new Intent(getApplicationContext(),UpdateInfo.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Log in unsuccessful!!",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    };
}