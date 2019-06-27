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

public class withoutlocationdialog extends AppCompatDialogFragment {
    private withoutlocationinterface listener;
    private EditText cat,forc,dat;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.nolocationsearch,null);
        builder.setView(view).setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              String noloc_cat=cat.getText().toString();
              String noloc_forc=forc.getText().toString();
              String noloc_dat=dat.getText().toString();
              if (noloc_cat.isEmpty()&&noloc_forc.isEmpty()&&noloc_dat.isEmpty()){
                  Toast.makeText(getActivity(),"not valid credentials",Toast.LENGTH_SHORT).show();
              }
else {
                  listener.gotomap(noloc_cat, noloc_forc, noloc_dat);
              }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Cancel",Toast.LENGTH_SHORT).show();

            }
        });
        cat=view.findViewById(R.id.category);
        forc=view.findViewById(R.id.force);
        dat=view.findViewById(R.id.date);


return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(withoutlocationinterface)context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implements withoutlocationlistener");
        }

    }

    public interface withoutlocationinterface{
        void gotomap(String string,String string1,String string2);
    }

}
