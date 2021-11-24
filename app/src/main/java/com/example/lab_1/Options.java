package com.example.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Options extends AppCompatActivity implements View.OnClickListener {
   Button icu,diagno,surgery,others;
   FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        this.setTitle("Options");

        icu=(Button)findViewById(R.id.ICUoptId);
        diagno=(Button)findViewById(R.id.DiagnosisoptId);
        surgery=(Button)findViewById(R.id.SurgerioptId);
        others=(Button)findViewById(R.id.ratingoptId);
        icu.setOnClickListener(this);
        diagno.setOnClickListener(this);
        surgery.setOnClickListener(this);
        others.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.signOutId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent=new Intent(getApplicationContext(),FirstPage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.ICUoptId)
        {
            Intent intent=new Intent(getApplicationContext(),ICU.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.DiagnosisoptId)
        {
            Intent intent=new Intent(getApplicationContext(),diagno.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.SurgerioptId)
        {
            Intent intent=new Intent(getApplicationContext(),Surgery.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.ratingoptId)
        {
            Intent intent=new Intent(getApplicationContext(),Rating.class);
            startActivity(intent);

        }

    }
}