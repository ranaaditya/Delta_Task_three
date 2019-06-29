package com.rana_aditya.delta_task_3;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class fragment_nolocation extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3="param3";
    public static final String BASE_URL = "https://data.police.uk/api/";
    public  Recyclerview_nolocation adapter;
    private RecyclerView recyclerView;
    private List<CrimeByLocation> list=new ArrayList<>();
    private RecyclerView.LayoutManager manager;
    private jsonrecordholder api;


    private String mParam1;
    private String mParam2;
    private String mparam3;




    public fragment_nolocation() {

    }


    public static fragment_nolocation newInstance(String param1, String param2,String string3) {
        fragment_nolocation fragment = new fragment_nolocation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3,string3);
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
        View view= inflater.inflate(R.layout.fragment_fragment_nolocation, container, false);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api=retrofit.create(jsonrecordholder.class);
        recyclerView=view.findViewById(R.id.recycler);
        manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        mParam1 = getArguments().getString(ARG_PARAM1);
        mParam2 = getArguments().getString(ARG_PARAM2);
        mparam3 = getArguments().getString(ARG_PARAM3);
        getcrimenolocation(mParam1,mParam2,mparam3);
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
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }


    public void  getcrimenolocation(String category,String force ,String date){
        Call<List<CrimeByLocation>> call=api.getcrimenolocation(category,force,date);
        call.enqueue(new Callback<List<CrimeByLocation>>() {
            @Override
            public void onResponse(Call<List<CrimeByLocation>> call, Response<List<CrimeByLocation>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity()," not successful api fetching done !!!",Toast.LENGTH_SHORT).show();
                }
                else {
                   list=response.body();
                    if (list==null){
                        Toast.makeText(getActivity(),"nothying to show",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        adapter=new Recyclerview_nolocation(getActivity(),list);
                        recyclerView.setAdapter(adapter);

                        Toast.makeText(getActivity(),"wait....",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CrimeByLocation>> call, Throwable t) {

            }
        });


    }



}
