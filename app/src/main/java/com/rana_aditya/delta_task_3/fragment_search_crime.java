//this is used in hackermode of the task...

package com.rana_aditya.delta_task_3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragment_search_crime extends Fragment {
    public static final String BASE_URL = "https://data.police.uk/api/";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private crimerecycleradapter adapter;
    private RecyclerView crimerecyclerview;

    public String mParam1;
    public String mParam2;
    public String mparam3;
    private ListView crimelistView;
    private jsonrecordholder api;
    TextView textView;



    public fragment_search_crime() {
        // Required empty public constructor
    }


    
    public static fragment_search_crime newInstance(String param1, String param2,String param3) {
        fragment_search_crime fragment = new fragment_search_crime();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3,param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mparam3 = getArguments().getString(ARG_PARAM3);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_search_crime, container, false);
        textView=view.findViewById(R.id.crimeheading);

        crimerecyclerview=view.findViewById(R.id.crimerecycler);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity());
        crimerecyclerview.setLayoutManager(manager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(jsonrecordholder.class);
        mParam1 = getArguments().getString(ARG_PARAM1);
        mParam2 = getArguments().getString(ARG_PARAM2);
        mparam3 = getArguments().getString(ARG_PARAM3);
        getcrimebylocation(mParam1,mParam2,mparam3);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.notifyItemChanged(viewHolder.getAdapterPosition());

                Toast.makeText(getContext(),"swipped it ",Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(crimerecyclerview);

        return view;
    }






    public  void getcrimebylocation(String a, String b, String c){
        Call<List<CrimeByLocation>> call=api.getcrimebylocation(a,b,c);
        call.enqueue(new Callback<List<CrimeByLocation>>() {
            @Override
            public void onResponse(Call<List<CrimeByLocation>> call, Response<List<CrimeByLocation>> response) {
                if (!response.isSuccessful()) {
                    // textView.setText("sorry type correct credentials ,try again later  ");
                    Toast.makeText(getActivity(),"not successfull",Toast.LENGTH_SHORT).show();
                }
                final List<CrimeByLocation> crimelisst = response.body();
                final String content;
                if (crimelisst == null) {
                    Toast.makeText(getActivity(), " CRIME FOR  GIVEN DATA DOESNOT EXIST ", Toast.LENGTH_SHORT).show();

                } else {

                    adapter = new crimerecycleradapter(crimelisst, getActivity());
                    crimerecyclerview.setAdapter(adapter);
                }

            }


            @Override
            public void onFailure(Call<List<CrimeByLocation>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }

}
