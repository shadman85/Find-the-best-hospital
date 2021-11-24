package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateInfo extends AppCompatActivity implements View.OnClickListener {
    private EditText hospitalName,pin,IcuCost,IcuAvailable,surgeryCost,ecgCost,XrayCost;
    private Button icu,icu2,sg,ecg,xray;
  DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        this.setTitle("Information Update Page");

        pin=(EditText) findViewById(R.id.pinId);
        IcuAvailable=(EditText)findViewById(R.id.availableIcuId);
        IcuCost=(EditText) findViewById(R.id.icucostId);
        surgeryCost=(EditText)findViewById(R.id.surgerycostId);
        ecgCost=(EditText)findViewById(R.id.ecgcostId);
        XrayCost=(EditText)findViewById(R.id.xraycostId);
        icu=(Button)findViewById(R.id.updicuId);
        icu2=(Button)findViewById(R.id.updicucostId);
        sg=(Button)findViewById(R.id.updsurgeryId);
        ecg=(Button)findViewById(R.id.updecgId);
        xray=(Button)findViewById(R.id.updxrayId);
        icu.setOnClickListener(this);
        icu2.setOnClickListener(this);
        sg.setOnClickListener(this);
        ecg.setOnClickListener(this);
        xray.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.updicuId)
        {
            String id=pin.getText().toString().trim();
            String vicu=IcuAvailable.getText().toString().trim();
            databaseReference= FirebaseDatabase.getInstance().getReference("Hospital").child(id);
            databaseReference.child("icu").setValue(vicu);
            IcuAvailable.getText().clear();
            Toast.makeText(getApplicationContext(),"Updated Succesfullu",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.updicucostId)
        {
            String id=pin.getText().toString().trim();
            String icuC=IcuCost.getText().toString().trim();
            databaseReference= FirebaseDatabase.getInstance().getReference("Hospital").child(id);
            databaseReference.child("icuCost").setValue(icuC);
            IcuCost.getText().clear();
            Toast.makeText(getApplicationContext(),"Updated Succesfullu",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.updsurgeryId)
        {
            String id=pin.getText().toString().trim();
            String sgr= surgeryCost.getText().toString().trim();
            databaseReference= FirebaseDatabase.getInstance().getReference("Hospital").child(id);
            databaseReference.child("surgeryCost").setValue(sgr);
            surgeryCost.getText().clear();
            Toast.makeText(getApplicationContext(),"Updated Succesfullu",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.updecgId)
        {
            String id=pin.getText().toString().trim();
            String ecgc=ecgCost.getText().toString().trim();
            databaseReference= FirebaseDatabase.getInstance().getReference("Hospital").child(id);
            databaseReference.child("ecgCost").setValue(ecgc);
            ecgCost.getText().clear();
            Toast.makeText(getApplicationContext(),"Updated Succesfullu",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.xraycostId)
        {
            String id=pin.getText().toString().trim();
            String xry=XrayCost.getText().toString().trim();
            databaseReference= FirebaseDatabase.getInstance().getReference("Hospital").child(id);
            databaseReference.child("xrayCost").setValue(xry);
            XrayCost.getText().clear();
            Toast.makeText(getApplicationContext(),"Updated Succesfullu",Toast.LENGTH_SHORT).show();
        }

    }
}