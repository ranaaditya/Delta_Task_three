
//created for normal mode ...
package com.rana_aditya.delta_task_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class favcrimelistadapter extends BaseAdapter {
    Context context;
    private LayoutInflater inflater;
    private List<favcrime> list_force;
    private TextView name,id;

    public favcrimelistadapter(Context context, List<favcrime> list_force) {
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
                    inflate(R.layout.favcrimelistlayout, parent, false);

            name=convertView.findViewById(R.id.crime_cat);
            id=convertView.findViewById(R.id.crime_id);
        }



        id.append(""+this.list_force.get(position).getId());
        name.append(this.list_force.get(position).getCategory().toLowerCase());
        return convertView;
    }
}
