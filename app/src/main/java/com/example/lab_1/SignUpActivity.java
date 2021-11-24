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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email,username,password;
    private Button signUp;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up Activity");
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.signupEmailId);
        username = (EditText) findViewById(R.id.signUpUsernameID);
        password = (EditText) findViewById(R.id.signUppasswrdID);
        signUp = (Button) findViewById(R.id.regSignUpId);
        progressBar= findViewById(R.id.signUpProgressId);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.regSignUpId)
        {
            //Toast.makeText(SignUpActivity.this,"Registration Successful!",Toast.LENGTH_LONG).show();
         String Email=email.getText().toString().trim();
         String Password=password.getText().toString().trim();
         if(Email.isEmpty())
         {
             email.setError("Enter an email address !");
             email.requestFocus();
         }
         else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
         {
             email.setError("Enter a valid email address !");
             email.requestFocus();
         }
         if(Password.isEmpty())
         {
             password.setError("Please enter a password!");
             email.requestFocus();
         }
         else if(Password.length()<8)
         {
             password.setError("Password length should be minimum 8!");
             email.requestFocus();
         }
         progressBar.setVisibility(View.VISIBLE);
         mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                 progressBar.setVisibility(View.GONE);
                 if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Registraion successful",Toast.LENGTH_LONG).show();
                 } else {
                     if (task.getException() instanceof FirebaseAuthUserCollisionException)
                     {
                         Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_LONG).show();

                     }
                     else
                     {
                         Toast.makeText(getApplicationContext(),"Error :"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                     }
                 }
             }
         });
            Intent intent=new Intent(SignUpActivity.this,Options.class);
            startActivity(intent);
        }
    }


}