package com.rana_aditya.delta_task_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class searchdialog extends AppCompatDialogFragment {
    private favcrimeinterface listener;
    private TextView textView;
    private String searchcontentdata;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.searchdialog,null);
        builder.setView(view).setTitle("search database")
                .setPositiveButton("search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchcontentdata=textView.getText().toString();
                        if (!searchcontentdata.isEmpty())
                        listener.gotosearcgdatabase(searchcontentdata);

                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"CANCEL",Toast.LENGTH_SHORT).show();
            }
        });
        textView=view.findViewById(R.id.searchdialogedittext);
    return builder.create();
    }

    public interface favcrimeinterface{
        void gotosearcgdatabase(String string);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(favcrimeinterface)context;
        }catch (ClassCastException e){
            throw new ClassCastException( context.toString()+"mmust implements favcrimeinterface ");
        }
    }
}
