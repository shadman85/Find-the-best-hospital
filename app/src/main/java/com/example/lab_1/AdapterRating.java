package com.example.lab_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterRating extends RecyclerView.Adapter<AdapterRating.ViewHolder> {
    private List<Hospital> list;
    private Context context;

    public AdapterRating(List<Hospital> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterRating.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ratinglistlayout,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterRating.ViewHolder holder, int position) {
        holder.id.setText(list.get(position).getId());
        holder.name.setText(list.get(position).getHospital_name());
        holder.rating.setText(list.get(position).getRating());
       /*  String tmp=list.get(position).getRating();
         double a=Double.parseDouble(tmp);
         double b=Math.floor(a);
         double c=b;b=b*100;
        a=a*100;
        a=Math.floor(a);
         double d=a-b;*/



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id,name,rating;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.ratinglistIDId);
            name=itemView.findViewById(R.id.ratinglistNameId);
            rating=itemView.findViewById(R.id.ratingId);
        }
    }
}
