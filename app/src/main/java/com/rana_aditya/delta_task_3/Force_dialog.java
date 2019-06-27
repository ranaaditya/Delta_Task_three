package com.rana_aditya.delta_task_3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Force_dialog extends AppCompatDialogFragment {
    EditText searchcontent;
private mydialoginterface listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.forcedialog,null);
        builder.setView(view).setTitle("search specific force")
                .setPositiveButton("search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String force_name=searchcontent.getText().toString();
                      listener.applytext(force_name);
                    }
                }).setNegativeButton("back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
searchcontent=view.findViewById(R.id.dialog_force_name);
return builder.create();
    }
    public interface mydialoginterface{
         void applytext(String str);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
    listener=(mydialoginterface)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+"must implement mydialog interface listener");
        }
    }



}

