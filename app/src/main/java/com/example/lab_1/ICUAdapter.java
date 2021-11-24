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

public class ICUAdapter extends FirebaseRecyclerAdapter<Hospital,ICUAdapter.ViewHolder> {
  //Context context;
 // List<Hospital> hospitalList;
  public ICUAdapter(
          @NonNull FirebaseRecyclerOptions<Hospital> options)
  {
      super(options);
  }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position,Hospital hospital) {


            holder.name.setText(hospital.getHospital_name());
            holder.amount.setText(hospital.getIcu());
            holder.cost.setText(hospital.getIcuCost());
       // Log.d("test1",hospital.getIcu()+"");
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iculayout,parent,false);
       return new ICUAdapter.ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,amount,cost;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.icuNameId);
            amount=itemView.findViewById(R.id.icuNumId);
            cost=itemView.findViewById(R.id.icuCstId);

        }
    }
}
