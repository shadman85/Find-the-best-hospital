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

import com.example.lab_1.Hospital;
import com.example.lab_1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.List;

public class DgnAdapter extends FirebaseRecyclerAdapter<Hospital, com.example.lab_1.DgnAdapter.ViewHolder> {

    public DgnAdapter(
            @NonNull FirebaseRecyclerOptions<Hospital> options)
    {
        super(options);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull com.example.lab_1.DgnAdapter.ViewHolder holder, int position, Hospital hospital) {

        holder.name.setText(hospital.getHospital_name());
        holder.ecgcost.setText(hospital.getEcgCost());
        holder.xraycost.setText(hospital.getXrayCost());
        // Log.d("test1",hospital.getIcu()+"");
    }


    @NonNull
    @NotNull
    @Override
    public com.example.lab_1.DgnAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dgnlayout,parent,false);
        return new com.example.lab_1.DgnAdapter.ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,ecgcost,xraycost;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.dgnnameId);
            ecgcost=itemView.findViewById(R.id.ecgdgnId);
            xraycost=itemView.findViewById(R.id.xraydgnCstId);

        }
    }
}
