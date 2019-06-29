package com.rana_aditya.delta_task_3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;


public class fragment_crimedetails extends Fragment {

    private CrimeByLocation object;
    TextView crimetext;


    public fragment_crimedetails() {

    }


 
    public static fragment_crimedetails newInstance(CrimeByLocation object) {
        fragment_crimedetails fragment = new fragment_crimedetails();
        Bundle args = new Bundle();
        args.putSerializable("object",object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
         object= (CrimeByLocation) getArguments().getSerializable("object");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_crimedetails, container, false);
        crimetext=view.findViewById(R.id.crimedetailtextview);
        object= (CrimeByLocation) getArguments().getSerializable("object");
        String content;
        if (object==null){
            crimetext.setText(" !!! DETAILS ABOUT THIS CRIME HAS BEEN DELETED BY ADMIN !!!");
        }
        else {
            try{
String persistent_id=object.getPersistent_id();
if (persistent_id==null){
    persistent_id="null";
}else
{
    persistent_id= Jsoup.parse(persistent_id).text();
}
                content="\n CATEGORY - "+object.getCategory()+"\n Location_Type -"+object.getLocation_type()+"\n Latitude - "+object.getLocation().getLatitude()+"\n Longitude  - "+object.getLocation().getLongitude()+"\n Street_Area - "+object.getLocation().getStreet().getName()+"\n PERSISTENCE_ID - "+persistent_id   +"\n id "+object.getId()+"\n MONTH - "+object.getMonth()+"\n";
                crimetext.append(content);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return view;
    }


}


