package com.rana_aditya.delta_task_3;

import android.content.Intent;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listact extends AppCompatActivity {
    private jsonrecordholder api;
    TextView textView;
    private  String spec_name;
    private static final String BASE_URL = "https://data.police.uk/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listact);
        Intent intent=getIntent();
      spec_name=  intent.getStringExtra("spec_name");

        textView=findViewById(R.id.text_about);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(jsonrecordholder.class);
        getspec_force(spec_name);




    }



    public void getspec_force(String name ) {
        Call<spec_force> call=api.getspec_force(name);
        call.enqueue(new Callback<spec_force>() {
            @Override
            public void onResponse(Call<spec_force> call, Response<spec_force> response) {
                if (!response.isSuccessful()){
                    textView.setText("SORRY ,THIS NAME DOESNOT EXIST ,TRY CORRECT ONE ");
                }

                spec_force specs= response.body();
                String con;
                if (specs==null){
                    Toast.makeText(listact.this,"this name doesnot exist",Toast.LENGTH_SHORT).show();
                }
else {
                    con = "\n\n Name : " + specs.getName() + "\n" + " \n DESCRIPTION : " + specs.getDescription() + "\n URL : " + specs.getUrl() + "\n TELEPHONE : " + specs.getTelephone() + "\n Id : " + specs.getId();
                    textView.append(Html.fromHtml(con));
                }


            }


            @Override
            public void onFailure(Call<spec_force> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }



}
