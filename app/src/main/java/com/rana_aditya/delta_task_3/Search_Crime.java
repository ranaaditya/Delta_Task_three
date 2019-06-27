package com.rana_aditya.delta_task_3;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search_Crime extends AppCompatActivity {
    private static final String BASE_URL = "https://data.police.uk/api/";
    private jsonrecordholder api;
    TextView textView;
    private String date;
    private String latitude;
    private String longitude;
    private ListView crimelistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__crime);
        crimelistView=findViewById(R.id.crimelist);
        Intent intent=getIntent();
       // textView=findViewById(R.id.crimetext);
        date=intent.getStringExtra("datebymonth");
        latitude=intent.getStringExtra("to_latitude");
        longitude=intent.getStringExtra("to_longitude");
        Intent intent1=getIntent();
        date=intent.getStringExtra("dateofcrime");
        latitude=intent.getStringExtra("latitudeofcrime");
        longitude=intent.getStringExtra("longitudeofcrime");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(jsonrecordholder.class);

getcrimebylocation(date,latitude,longitude);

    }


    public  void getcrimebylocation(String a,String b,String c){
        Call<List<CrimeByLocation>> call=api.getcrimebylocation(a,b,c);
        call.enqueue(new Callback<List<CrimeByLocation>>() {
            @Override
            public void onResponse(Call<List<CrimeByLocation>> call, Response<List<CrimeByLocation>> response) {
                if (!response.isSuccessful()) {
                  // textView.setText("sorry type correct credentials ,try again later  ");
                    Toast.makeText(Search_Crime.this,"not successfull",Toast.LENGTH_SHORT).show();
                }
                final List<CrimeByLocation> crimelisst = response.body();
                final String content;
                if (crimelisst == null) {
                    Toast.makeText(Search_Crime.this, " CRIME FOR  GIVEN DATA DOESNOT EXIST ", Toast.LENGTH_SHORT).show();

                } else {

                    crimelistadapter adapter=new crimelistadapter(Search_Crime.this,crimelisst);
                    crimelistView.setAdapter(adapter);
                    crimelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            CrimeByLocation object=crimelisst.get(position);
                            Intent intent=new Intent(Search_Crime.this,crimedetails.class);
                            intent.putExtra("object_data",object);
                            startActivity(intent);
                        }
                    });


                    crimelistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                            CrimeByLocation save_crime=crimelisst.get(position);
                            ContentValues values=new ContentValues();
                            values.put("crime_id",save_crime.getId());
                            values.put("crime_category",save_crime.getCategory());
                            Uri uri =getContentResolver().insert(MyContentProvider.CONTENT_URI,values);
                            if (uri!=null)
                            Toast.makeText(Search_Crime.this," SAVED ",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                    /*for (CrimeByLocation crime1 : crime) {

                        content = "\ncategory :  " + crime1.getCategory().toUpperCase() + "\n" + "Month :" + crime1.getMonth() + "\n";
                        textView.append(content);

                    }*/
                }
            }

            @Override
            public void onFailure(Call<List<CrimeByLocation>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }

}

