package com.rana_aditya.delta_task_3;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class crimedetails extends AppCompatActivity {
    TextView crimetext;
private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimedetails);
        CrimeByLocation object=(CrimeByLocation)getIntent().getSerializableExtra("object_data");
        crimetext=findViewById(R.id.crimedetailtextview);
        if (object==null){
            crimetext.setText(" !!! DETAILS ABOUT THIS CRIME HAS BEEN DELETED BY ADMIN !!!");
        }
        else {
            try{
        content="\n CATEGORY - "+object.getCategory()+"\n Location_Type -"+object.getLocation_type()+"\n Latitude - "+object.getLocation().getLatitude()+"\n Longitude  - "+object.getLocation().getLongitude()+"\n Street_Area - "+object.getLocation().getStreet().getName()+"\n PERSISTENCE_ID - "+object.getPersistence_id()+"\n id "+object.getId()+"\n MONTH - "+object.getMonth()+"\n";
        crimetext.append(content);
        }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

    }
}


//