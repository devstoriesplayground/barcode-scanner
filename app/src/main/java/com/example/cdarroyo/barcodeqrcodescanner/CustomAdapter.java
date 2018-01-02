package com.example.cdarroyo.barcodeqrcodescanner;

/**
 * Created by cdarroyo on 12/15/2017.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> userID;
    ArrayList<String> userScan;

    myDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;

    String result_tag,id;

    public CustomAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> scan
    )
    {

        this.context = context2;
        this.userID = id;
        this.userScan = scan;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(final int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.list_row_item, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);

            child.setTag(holder);

            child.setOnLongClickListener(new AdapterView.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Scanner");
                    alertDialogBuilder.setMessage("Do you want to delete this item?");
                    alertDialogBuilder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    id = userID.get(position);
                                    result_tag = userScan.get(position);
                                    myDbHelper=new myDbHelper(context);
                                    sqLiteDatabase = myDbHelper.getWritableDatabase();
                                    myDbHelper.deleteinformation(id,result_tag,sqLiteDatabase);
                                    Toast.makeText(context,"You deleted item " + id + " " + result_tag ,Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                    myDbHelper.close();

                                    Intent i = new Intent(context, MainActivity.class);
                                    context.startActivity(i);
                                }
                            });

                    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    return false;
                }

            });

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userID.get(position));
        holder.textviewname.setText(userScan.get(position));

        return child;

    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
    }

}