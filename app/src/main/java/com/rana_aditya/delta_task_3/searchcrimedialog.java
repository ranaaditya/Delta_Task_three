package com.rana_aditya.delta_task_3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class searchcrimedialog extends AppCompatDialogFragment  {

private EditText date;
private EditText lati;
private EditText longi;
private mycrimesearchinterface listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.crimesearch,null);
        builder.setView(view).setTitle("search crime  here ")
                .setPositiveButton("search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String string=date.getText().toString();
                        String string1=lati.getText().toString();
                        String string2=longi.getText().toString();
                        if(string.isEmpty()||string1.isEmpty()||string2.isEmpty()||string.length()<7) {
                            Toast.makeText(getActivity(),"invalid credentials ,try again ",Toast.LENGTH_SHORT).show();
                        }else
                        listener.applycrime(string,string1,string2);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity()," CANCEL ",Toast.LENGTH_SHORT).show();
            }
        }).setNeutralButton("search without location", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String string=date.getText().toString();
                String string1=lati.getText().toString();
                String string2=longi.getText().toString();
                if (string1.isEmpty()&&string2.isEmpty()){
                    openwithoutlocationdialog();
                    Toast.makeText(getActivity()," yeah that's right choice ",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getActivity(),"that's not possible ",Toast.LENGTH_SHORT).show();


            }
        });
       date=view.findViewById(R.id.datemonth);
       lati=view.findViewById(R.id.latitudeofcrime);
       longi=view.findViewById(R.id.longitudeofcrime);
        return builder.create();
    }
    public interface mycrimesearchinterface{
        void applycrime(String str,String str1,String str2);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(mycrimesearchinterface) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+"must implement mydialog interface listener");
        }
    }

private void openwithoutlocationdialog(){
        withoutlocationdialog dialog=new withoutlocationdialog();
        dialog.show(getFragmentManager(),"withoutlocationdialog");
}



}
