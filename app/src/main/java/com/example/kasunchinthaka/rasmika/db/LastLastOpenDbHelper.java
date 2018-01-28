package com.example.kasunchinthaka.rasmika.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;



public class LastLastOpenDbHelper  extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG ="LastLastOpenDbHelper";

    public LastLastOpenDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Create Tables in the DB
    private static final String DATABASE_CREATE = "create table " + "LOGIN" + "( "
            +_ID+ " integer primary key autoincrement,"
            + "USERNAME  text,PASSWORD  text,USER_EMAIL  text,POSITION  text); ";

    //to put completeness of the level

    private static final String DATABASE_CREATE2 = "create table " + "LevelComplete" + "( "     //login
            +_ID+ " integer primary key autoincrement,"
            + "COMPLETENESS  text,LEVEL text); ";
    //----------------

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i(TAG,DATABASE_CREATE);


        //---------------------
        db.execSQL(DATABASE_CREATE2);
        Log.i(TAG,DATABASE_CREATE2);
        //------------------------
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 'LOGIN'; ");
        Log.i(TAG,"DROP TABLE IF EXISTS 'LOGIN'; ");
        onCreate(db);

    }
}
