package com.example.lab_1;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.List;

public class SRGAdapter extends FirebaseRecyclerAdapter<Hospital,SRGAdapter.ViewHolder> {

    public SRGAdapter(
            @NonNull FirebaseRecyclerOptions<Hospital> options)
    {
        super(options);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position,Hospital hospital) {

        holder.name.setText(hospital.getHospital_name());

        holder.cost.setText(hospital.getSurgeryCost());
        // Log.d("test1",hospital.getIcu()+"");
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.srglayout,parent,false);
        return new SRGAdapter.ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cost;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.srgnameId);

            cost=itemView.findViewById(R.id.srgcostId);

        }
    }
}
