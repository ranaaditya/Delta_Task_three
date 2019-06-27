package com.rana_aditya.delta_task_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class crimelistadapter extends BaseAdapter {


    Context context;
    private LayoutInflater inflater;
    private List<CrimeByLocation> list_force;
    private TextView name,id;

    public crimelistadapter(Context context, List<CrimeByLocation> list_force) {
        this.context = context;
        this.list_force = list_force;

    }


    @Override
    public int getCount() {
        return list_force.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.crimelistlayout, parent, false);
            name=convertView.findViewById(R.id.crime_list_month);
            id=convertView.findViewById(R.id.crime_list_cat);
        }


        id.setText("cat - " +this.list_force.get(position).getCategory());
        name.append(this.list_force.get(position).getMonth().toUpperCase());
        return convertView;
    }

}
