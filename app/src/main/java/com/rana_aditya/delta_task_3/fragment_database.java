package com.rana_aditya.delta_task_3;


import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class fragment_database extends Fragment {
    private ListView listView;
    private List<favcrime> datacrime=new ArrayList<>();
    private favcrimelistadapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String string;

    private String mParam1;
    private String mParam2;



    public fragment_database() {

    }



    public static fragment_database newInstance(String param1) {
        fragment_database fragment = new fragment_database();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_database, container, false);
        listView=view.findViewById(R.id.searchedlist);
      string=getArguments().getString(ARG_PARAM1);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadmoredata(string);
    }

    public void loadmoredata(String str){


        if (str==null)
            Toast.makeText(getActivity(), "strinhg is  null" , Toast.LENGTH_SHORT).show();

        Cursor cr=getActivity().getApplicationContext().getContentResolver().query(MyContentProvider.CONTENT_URI,null,null,null,"_id");
        // Cursor cr=getContentResolver().query(MyContentProvider.CONTENT_URI,null,"? = ?",new String[]{" crime_category ",str},"_id");
        if (cr!=null) {

            while (cr.moveToNext()) {
                String i = cr.getString(1);
                String string85 = cr.getString(2);
                favcrime obj = new favcrime(i, string85);
                datacrime.add(obj);
            }
            adapter=new favcrimelistadapter(getActivity(),datacrime);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(),"hihihihihihih",Toast.LENGTH_SHORT).show();

        }else

            Toast.makeText(getActivity()," !!! NO CRIME SAVED HERE got it !!!",Toast.LENGTH_SHORT).show();


    }


}
