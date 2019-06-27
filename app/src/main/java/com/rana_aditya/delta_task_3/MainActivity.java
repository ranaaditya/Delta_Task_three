package com.rana_aditya.delta_task_3;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Force_dialog.mydialoginterface,searchcrimedialog.mycrimesearchinterface,withoutlocationdialog.withoutlocationinterface {
    public static final String BASE_URL = "https://data.police.uk/api/";

    private TextView Heading;
    private jsonrecordholder api;
    private ListView listView;
    private listAdapter adapter;
    FloatingActionButton search;
    FloatingActionButton crimebtn;
    public String specname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Heading = findViewById(R.id.heading);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(jsonrecordholder.class);
        listView = findViewById(R.id.listview);
        search = findViewById(R.id.floatingbtn);
        crimebtn = findViewById(R.id.crime_finder);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        crimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencrimefinder();

            }
        });


        getforces();
        //getspec_force();
        //getlastupdate();

        //getcrimebylocation("2017-01","52.629729","-1.131592");




    }

    private void getforces() {
        Call<List<Force>> call = api.getforces();
        call.enqueue(new Callback<List<Force>>() {
            @Override
            public void onResponse(Call<List<Force>> call, Response<List<Force>> response) {
                if (!response.isSuccessful()) {
                    Heading.setText("code" + response.code());
                }

                    final List<Force> forcce = response.body();
                   if (forcce!=null) Heading.setText("FORCES IN UK");
                    adapter = new listAdapter(MainActivity.this, forcce);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Force specific_force = forcce.get(position);
                            Intent intent = new Intent(MainActivity.this, listact.class);
                            intent.putExtra("spec_name", specific_force.getId());
                            startActivity(intent);
                        }
                    });
                }





            @Override
            public void onFailure(Call<List<Force>> call, Throwable t) {

                Toast.makeText(MainActivity.this, " !!! SORRY CHECK YOUR INTERNET CONNETION ...", Toast.LENGTH_SHORT).show();
                Heading.setText("NO INTERNET CONNECTION ...");
            }
        });
    }

    /*  public void getspec_force(String name ) {
          Call<spec_force>call=api.getspec_force(name);
          call.enqueue(new Callback<spec_force>() {
              @Override
              public void onResponse(Call<spec_force> call, Response<spec_force> response) {
                  if (!response.isSuccessful()){
                      textView.setText("  code  "+response.code());
                  }

                  spec_force specs= response.body();
                  String content;


                  content= "DESCRIPTION : "+specs.getDescription()+"\n URL : "+specs.getUrl()+"\n TELEPHONE : "+specs.getTelephone()+"\n Id : "+specs.getId()+"\n Name : "+specs.getName()+"\n";
                  textView.append(content);

              }


              @Override
              public void onFailure(Call<spec_force> call, Throwable t) {
  textView.setText(t.getMessage());
              }
          });

      }*/
 /*   private  void getlastupdate(){
        Call<Lastupdated>call=api.getlastupdate();
        call.enqueue(new Callback<Lastupdated>() {
            @Override
            public void onResponse(Call<Lastupdated> call, Response<Lastupdated> response) {
                if (!response.isSuccessful()){
                    textView.setText(response.code());
                }
                Lastupdated spec=response.body();
                String content="\n  LAST UPDATEED ON  : "+spec.getDate()+"\n";
                textView.append(content);

            }

            @Override
            public void onFailure(Call<Lastupdated> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }
*/
/*
  public  void getcrimebylocation(String a,String b,String c){
        Call<List<CrimeByLocation>>call=api.getcrimebylocation(a,b,c);
        call.enqueue(new Callback<List<CrimeByLocation>>() {
            @Override
            public void onResponse(Call<List<CrimeByLocation>> call, Response<List<CrimeByLocation>> response) {
                if (!response.isSuccessful()){
                    textView.setText("code : "+response.code());
                }
                List<CrimeByLocation> crime=response.body();
                String content;
                if (crime!=null)
                for (CrimeByLocation crime1 : crime) {
                    content = "\ncategory :  "+crime1.getCategory().toUpperCase()+"\n"+"Month :"+crime1.getMonth()+"\n";
                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<CrimeByLocation>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }*/
    public void openDialog() {
        Force_dialog dialog = new Force_dialog();
        dialog.show(getSupportFragmentManager(), "dialog_fragment");

    }

    @Override
    public void applytext(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        specname = str;
        Intent intent = new Intent(MainActivity.this, listact.class);
        intent.putExtra("spec_name", specname);
        startActivity(intent);
    }

    public void applycrime(String string, String string1, String string2) {

        Toast.makeText(this, "Searching Crimes By Location ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Search_Crime.class);
        intent.putExtra("dateofcrime", string);
        intent.putExtra("latitudeofcrime", string1);
        intent.putExtra("longitudeofcrime", string2);
        startActivity(intent);
    }

    public void opencrimefinder() {
        searchcrimedialog dialog = new searchcrimedialog();
        dialog.show(getSupportFragmentManager(), "crimefinderdialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.savefolder:
                Intent intent = new Intent(MainActivity.this, Favourite_crime.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void gotomap(String string, String string1, String string2) {
        Intent intent=new Intent(this,nolocationcrime.class);
        intent.putExtra("cat",string);
        intent.putExtra("forc",string1);
        intent.putExtra("dat",string2);
        startActivity(intent);
    }


}


