package com.rana_aditya.delta_task_3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recyclerview_nolocation extends RecyclerView.Adapter<Recyclerview_nolocation.ViewHolder>{

    List<CrimeByLocation> lisr;
    Context context;
    public Recyclerview_nolocation(Context contrext,List<CrimeByLocation>list) {
this.context=contrext;
        this.lisr=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowview= LayoutInflater.from(parent.getContext()).inflate(R.layout.nolocrecycler,null);
        return new ViewHolder(rowview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.textView_cat.setText("Cat - "+lisr.get(position).getCategory());
holder.textView_forc.setText("id - "+lisr.get(position).getId());
holder.textView_dat.setText("Month -"+ lisr.get(position).getMonth());
    }


    @Override
    public int getItemCount() {
        return lisr.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_cat,textView_forc,textView_dat,mapviewtext;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_cat=itemView.findViewById(R.id.noloccategory);
            textView_forc=itemView.findViewById(R.id.nolocforce);
            textView_dat=itemView.findViewById(R.id.nolocdate);
            mapviewtext=itemView.findViewById(R.id.nolocmap);
            mapviewtext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,mapview.class);
              intent.putExtra("lat",52.629729);
              intent.putExtra("lng",-1.131592);
              context.startActivity(intent);

                }
            });


        }
    }
}
