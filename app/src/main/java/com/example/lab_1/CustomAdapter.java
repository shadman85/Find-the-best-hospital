package com.example.lab_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Hospital> {
    private Activity context;
    private List<Hospital> hospitalList;



    public CustomAdapter(Activity context, List<Hospital> hospitalList) {
        super(context, R.layout.llistlayout, hospitalList);
        this.context=context;
        this.hospitalList=hospitalList;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
         View view=layoutInflater.inflate(R.layout.listofrating,null, true);
        Hospital hospital=hospitalList.get(position);
        TextView t1=view.findViewById(R.id.listViewratingIDId);
        TextView t2=view.findViewById(R.id.listViewratingNameId);
        TextView t3=view.findViewById(R.id.listViewratingRatingId);


        t1.setText(hospital.getId());
        t2.setText(hospital.getHospital_name());
        t3.setText(hospital.getRating());

        return view;
    }
}
