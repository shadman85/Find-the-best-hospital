package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity implements View.OnClickListener {
    private Button user,hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        user=(Button)findViewById(R.id.UserButtonId);
        hospital=(Button)findViewById(R.id.AuthorityButtonId);
        user.setOnClickListener(this);
        hospital.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.UserButtonId)
        {
            Intent intent=new Intent(FirstPage.this,MainActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.AuthorityButtonId)
        {
            Intent intent=new Intent(FirstPage.this,Authority_logIn.class);
            startActivity(intent);
        }
    }
}