package com.rana_aditya.delta_task_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class nolocationcrime extends AppCompatActivity {
    public static final String BASE_URL = "https://data.police.uk/api/";
    private Recyclerview_nolocation adapter;
    private RecyclerView recyclerView;
    private List<CrimeByLocation> list=new ArrayList<>();
    RecyclerView.LayoutManager manager;
    private String  cat,dat,forc;

    jsonrecordholder api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nolocationcrime);
        Intent intent=getIntent();
        cat=intent.getStringExtra("cat");
        forc=intent.getStringExtra("forc");
        dat=intent.getStringExtra("dat");
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api=retrofit.create(jsonrecordholder.class);
        recyclerView=findViewById(R.id.recycler);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        getcrimenolocation(cat,forc,dat);






    }
   public void  getcrimenolocation(String category,String force ,String date){
        Call<List<CrimeByLocation>> call=api.getcrimenolocation(category,force,date);
        call.enqueue(new Callback<List<CrimeByLocation>>() {
            @Override
            public void onResponse(Call<List<CrimeByLocation>> call, Response<List<CrimeByLocation>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(nolocationcrime.this," not successful api fetching done !!!",Toast.LENGTH_SHORT).show();
                }
                else {
                   list=response.body();
                   if (list==null){
                       Toast.makeText(nolocationcrime.this,"nothying to show",Toast.LENGTH_SHORT).show();
                   }
else {
                       adapter=new Recyclerview_nolocation(nolocationcrime.this,list);
                       recyclerView.setAdapter(adapter);
    Toast.makeText(nolocationcrime.this,"wait....",Toast.LENGTH_SHORT).show();
}
                }
            }

            @Override
            public void onFailure(Call<List<CrimeByLocation>> call, Throwable t) {

            }
        });


    }
}
