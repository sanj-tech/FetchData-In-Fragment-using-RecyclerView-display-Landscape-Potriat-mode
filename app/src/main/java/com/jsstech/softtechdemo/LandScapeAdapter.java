package com.jsstech.softtechdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.MyHolderView> {
    Context context;
    List<MyModel> list;

    public LandScapeAdapter(Context context,List<MyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_items,parent,false);
        return new MyHolderView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder,int position) {
        holder.textLCompName.setText(list.get(position).getName());
        holder.textLDuration.setText(list.get(position).getUsername());
        holder.textLpoints.setText(list.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        TextView textLCompName, textLDuration, textLpoints;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);

            textLCompName = itemView.findViewById(R.id.txtcomp_name1);
            textLDuration = itemView.findViewById(R.id.txtDuration1);
            textLpoints = itemView.findViewById(R.id.txtpoints1);
        }
    }
}
