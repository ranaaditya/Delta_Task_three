package com.rana_aditya.delta_task_3;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Favourite_crime extends AppCompatActivity implements searchdialog.favcrimeinterface{
private ListView crimelistview;
private List<favcrime> list_favcrime=new ArrayList<>();
   private  favcrimelistadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_crime);
        crimelistview=findViewById(R.id.favouritecrimelist);
            loaddatabase();




    }

    public void loaddatabase(){
        Cursor cr=getContentResolver().query(MyContentProvider.CONTENT_URI,null,null,null,"_id");
        try{
            list_favcrime.clear();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        if (cr!=null) {
            while (cr.moveToNext()) {
                int i = cr.getInt(1);
                String string = cr.getString(2);
                favcrime obj = new favcrime(i, string);
                list_favcrime.add(obj);
            }
             adapter=new favcrimelistadapter(Favourite_crime.this,list_favcrime);
            crimelistview.setAdapter(adapter);
        }else
            Toast.makeText(Favourite_crime.this," !!! NO CRIME SAVED HERE !!!",Toast.LENGTH_SHORT).show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.searchdatabase,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.searchdatbase:
                opensearchcrimedialog();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }
public void opensearchcrimedialog(){
        searchdialog dialog=new searchdialog();
        dialog.show(getSupportFragmentManager(),"searchindatabasefragment");
        Toast.makeText(Favourite_crime.this,"saved database ",Toast.LENGTH_SHORT).show();

}

    @Override
    public void gotosearcgdatabase(String string) {
Intent intent =new Intent(Favourite_crime.this,database.class);
if (string!=null) {
    intent.putExtra("abc", string);
    startActivity(intent);
}


    }
}
