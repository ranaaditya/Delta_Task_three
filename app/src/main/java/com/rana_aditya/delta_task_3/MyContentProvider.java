package com.rana_aditya.delta_task_3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider(){

    }
    public static final String DB_NAME="crime";
    public static final String  DB_TABLE="storage";
    public static final int  DB_VER=1;
    public static final String  AUTHORITY="com.rana_aditya.delta_task_3.appdatabase";
    public static final Uri  CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/storage");
    static int EMP=1;
    static  int EMP_ID=2;
    static UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static{

        uriMatcher.addURI(AUTHORITY,"emp",EMP);
        uriMatcher.addURI(AUTHORITY,"emp/#",EMP_ID);

    }
    public SQLiteDatabase mydb;

    private class mydatabase extends SQLiteOpenHelper {


        public mydatabase(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+DB_TABLE+" (_id integer primary key autoincrement , crime_id integer , crime_category text );");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists "+DB_TABLE);
        }
    }


    @Override
    public boolean onCreate() {
        mydatabase helper=new mydatabase(getContext());
        mydb=helper.getWritableDatabase();
     if (mydb!=null)return true;
     else
         return false;
    }


    @Override
    public Cursor query( Uri uri,  String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(DB_TABLE);
        Cursor cr = builder.query(mydb,projection,selection,selectionArgs,null,null,"_id");
        cr.setNotificationUri(getContext().getContentResolver(),uri);
        return cr;
    }



    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri, ContentValues values) {
        long row=mydb.insert(DB_TABLE,null,values);
        if (row>0)
            uri= ContentUris.withAppendedId(CONTENT_URI,row);
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete( Uri uri,  String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
    public Cursor getdata(String category_search){
        Cursor cursor=mydb.rawQuery("select * from "+DB_TABLE+" where crime_category %"+category_search,null);
        return cursor;
    }
}
