package com.rana_aditya.delta_task_3;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class database extends AppCompatActivity {
private ListView listView;
 private List<favcrime> datacrime=new ArrayList<>();
private favcrimelistadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Intent intent=getIntent();
        String string=intent.getStringExtra("abc");
        listView=findViewById(R.id.searchedlist);
        loadmoredata(string);
    }



    public void loadmoredata(String str){


            if (str==null)
                Toast.makeText(database.this, "strinhg is  null" , Toast.LENGTH_SHORT).show();

Cursor cr=getContentResolver().query(MyContentProvider.CONTENT_URI,null,null,null,"_id");
            //Cursor cr=getContentResolver().query(MyContentProvider.CONTENT_URI,null,"? = ?",new String[]{" crime_category ",str},"_id");
            if (cr!=null) {
                while (cr.moveToNext()) {
                    int i = cr.getInt(1);
                    String string85 = cr.getString(2);
                    favcrime obj = new favcrime(i, string85);
                    datacrime.add(obj);
                }
                adapter=new favcrimelistadapter(database.this,datacrime);
                listView.setAdapter(adapter);
                Toast.makeText(database.this,"hihihihihihih",Toast.LENGTH_SHORT).show();

            }else

                Toast.makeText(database.this," !!! NO CRIME SAVED HERE got it !!!",Toast.LENGTH_SHORT).show();


        }
        }




