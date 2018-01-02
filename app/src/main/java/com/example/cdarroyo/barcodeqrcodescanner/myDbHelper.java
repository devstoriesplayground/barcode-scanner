package com.example.cdarroyo.barcodeqrcodescanner;

/**
 * Created by cdarroyo on 12/18/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="USERINFO.DB";
    private static final int DATABASE_VERSION=5;
    private static final String CREATE_QUERY="CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME +"(id INTEGER, "+
            UserContract.NewUserInfo.USER_ID +" TEXT, "+ UserContract.NewUserInfo.USER_SCAN +" TEXT ); " ;


    public myDbHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATION", "Database created / opened.....");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATION", "Table create..."+CREATE_QUERY);
    }
    public void addinnformation(String id,String scan,SQLiteDatabase db){

        ContentValues contentValue = new ContentValues();
        contentValue.put(UserContract.NewUserInfo.USER_ID,id);
        contentValue.put(UserContract.NewUserInfo.USER_SCAN,scan);
        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValue);
        Log.e("DATABASE OPERATION", "One row is insert");
    }
    public void deleteinformation(String id, String scan, SQLiteDatabase db){
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + UserContract.NewUserInfo.TABLE_NAME+ " WHERE " + UserContract.NewUserInfo.USER_SCAN
                + "='" + scan + "'");
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}