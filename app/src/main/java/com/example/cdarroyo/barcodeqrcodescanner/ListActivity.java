package com.example.cdarroyo.barcodeqrcodescanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class ListActivity extends Activity {

    myDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    CustomAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> SCANRESULT_ArrayList = new ArrayList<String>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (ListView) findViewById(R.id.list_data);
        myDbHelper = new myDbHelper(this);
    }


    @Override
    protected void onResume() {

            ShowSQLiteDBdata() ;

            super.onResume();
    }

    private void ShowSQLiteDBdata() {


        sqLiteDatabase = myDbHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM table_scanresult", null);

        ID_ArrayList.clear();
        SCANRESULT_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(UserContract.NewUserInfo.USER_ID)));

                SCANRESULT_ArrayList.add(cursor.getString(cursor.getColumnIndex(UserContract.NewUserInfo.USER_SCAN)));


            } while (cursor.moveToNext());
        }

        ListAdapter = new CustomAdapter(ListActivity.this,

                ID_ArrayList,
                SCANRESULT_ArrayList

        );

        list.setAdapter(ListAdapter);

        cursor.close();
    }
}
