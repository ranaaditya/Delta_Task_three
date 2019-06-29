package com.rana_aditya.delta_task_3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragment_listact extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    public static final String BASE_URL = "https://data.police.uk/api/";



    private String mParam1;
    private jsonrecordholder api;
    private TextView textView;




    public fragment_listact( String string) {
        this.mParam1=string;

    }


    public static fragment_listact newInstance(String param1) {
        fragment_listact fragment = new fragment_listact(param1);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(jsonrecordholder.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_listact, container, false);

        textView=view.findViewById(R.id.nestedtextview);
        mParam1=getArguments().getString(ARG_PARAM1);
getspec_force(mParam1);
        return view; }






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
                    Toast.makeText(getActivity(),"this name doesnot exist",Toast.LENGTH_SHORT).show();
                }
                else {
                    String name=  Jsoup.parse(specs.getName()).text();
                    String desc=specs.getDescription();
                    if (desc==null){
                        desc="null";
                    }else {
                        desc=Jsoup.parse(specs.getDescription()).text();
                    }
                    String url=Jsoup.parse(specs.getUrl()).text();
                    String id=Jsoup.parse(specs.getId()).text();
                    int tel=specs.getTelephone();

                    con = "\n\n Name : " + name + "\n\n" + " \n DESCRIPTION : " + desc + "\n\n URL : " + url + "\n\n TELEPHONE : " + tel + "\n\n Id : " + id;

                    textView.append(con);
                }


            }


            @Override
            public void onFailure(Call<spec_force> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
}
