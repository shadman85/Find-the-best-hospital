package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username,passward;
    private Button signInButton;
    private TextView textView1,signup;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In Activity");
        mAuth = FirebaseAuth.getInstance();
        username=(EditText) findViewById(R.id.signInUsernameID);
        passward=(EditText) findViewById(R.id.signInPasswordID);
        textView1=(TextView) findViewById(R.id.txt1);
        signInButton =(Button) findViewById(R.id.signId);
        signup =(TextView) findViewById(R.id.signupId);
        progressBar= findViewById(R.id.signInProgressId);

        signInButton.setOnClickListener(this);
        signup.setOnClickListener(this);

         }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signId)
        {
            String Username=username.getText().toString().trim();
            String Password=passward.getText().toString().trim();
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
                passward.setError("Please enter a password!");
                passward.requestFocus();
            }
            else if(Password.length()<8)
            {
                passward.setError("Password length should be minimum 8!");
                passward.requestFocus();
            }
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful())
                    {
                        username.getText().clear();
                        passward.getText().clear();
                        finish();
                        Intent intent=new Intent(MainActivity.this,Options.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Sign in unsuccessful!!",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

        if(v.getId()==R.id.signupId)
        {
            Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);
        }

    }
}




