package com.example.kasunchinthaka.rasmika.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kasun Chinthaka on 11/21/2016.
 */

public class LastLastDataSource {
    private static final String TAG = "LastLastDataSource";
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbOpenHelper;
    private Context mContext;

    public LastLastDataSource(Context context) {
        this.mContext = context;
        mDbOpenHelper = new LastLastOpenDbHelper(context);
    }

    public void open(){
        mDatabase = mDbOpenHelper.getWritableDatabase();
        Log.i(TAG,"Database opened.");
    }

    public void close(){
        mDbOpenHelper.close();
        Log.i(TAG,"Database closed.");
    }

    public void insertEntry(String userName, String password,String userEmail,String position) {
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("USER_EMAIL", userEmail);
        newValues.put("POSITION", position);
        mDatabase.insertOrThrow("LOGIN", null, newValues);
    }
//--------------------------start
    public void insertEntry2(String completeness, String level) {
        ContentValues newValues = new ContentValues();
        newValues.put("COMPLETENESS", completeness);
        newValues.put("LEVEL", level);
        mDatabase.insertOrThrow("LevelComplete", null, newValues);
    }
    //----------------------

    public int deleteEntry(String UserName) {
        String where = "USERNAME=?";
        return mDatabase.delete("LOGIN", where,
                new String[]{UserName});
    }

    public String getSingleEntry(String userEmail) {
        Cursor cursor = mDatabase.query("LOGIN", null, " USER_EMAIL=?",
                new String[]{userEmail}, null, null, null);

        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public String getSingleEntryy(String password) {
        Cursor cursor = mDatabase.query("LOGIN", null, " PASSWORD=?",
                new String[]{password}, null, null, null);

        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String position = cursor.getString(cursor.getColumnIndex("POSITION"));
        cursor.close();
        return position;
    }
//-----------------------------------------start
    public String getSingleEntry2(String level) {
        Cursor cursor = mDatabase.query("LevelComplete", null, " LEVEL=?",
                new String[]{level}, null, null, null);

        if (cursor.getCount() < 1) {
            cursor.close();
            return "Not Completed";
        }
        cursor.moveToFirst();
        String completeness = cursor.getString(cursor.getColumnIndex("COMPLETENESS"));
        cursor.close();
        return completeness;
    }
    //-----------------------------------

    public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);
        String where = "USERNAME = ?";
        mDatabase.update("LOGIN", updatedValues, where, new String[]{userName});
    }

}
