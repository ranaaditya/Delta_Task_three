package com.rana_aditya.delta_task_3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;


public class fragment_nolocdeatils extends Fragment {
  CrimeByLocation object;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
private TextView textView;


    public fragment_nolocdeatils() {

    }


    public static fragment_nolocdeatils newInstance(CrimeByLocation object) {
        fragment_nolocdeatils fragment = new fragment_nolocdeatils();
        Bundle args = new Bundle();
      args.putSerializable("ARGS",object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
      object= (CrimeByLocation) getArguments().getSerializable("ARGS");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_fragment_nolocdeatils, container, false);
        object= (CrimeByLocation) getArguments().getSerializable("ARGS");
        textView=view.findViewById(R.id.text21);
        String content;
        if (object==null){
            textView.setText(" !!! DETAILS ABOUT THIS CRIME HAS BEEN DELETED BY ADMIN !!!");
        }
        else {
            try{
                String persistent_id=object.getPersistent_id();
                if (persistent_id==null){
                    persistent_id="null";
                }else {
                    persistent_id= Jsoup.parse(persistent_id).text();
                }
                String category=object.getCategory();
                if (category!=null){
                    category=Jsoup.parse(category).text();
                }
                content="\n\n CATEGORY - "+category+"date"+object.getOutcome_status().getDate()+"\n\noutcome_category"+object.getOutcome_status().getCategoty()+"\n\n PERSISTENCE_ID - "+persistent_id+"\n\n id "+object.getId()+"\n\n" +
                        "MONTH - "+object.getMonth()+"\n\n";

                                                  textView.setText(content);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        return view;
    }




}
