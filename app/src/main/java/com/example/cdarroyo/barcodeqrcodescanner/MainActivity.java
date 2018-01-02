package com.example.cdarroyo.barcodeqrcodescanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.ex;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class MainActivity extends Activity {

    TextView result;
    Button scan,list;
    String id,result_tag;

    final int REQ_CODE = 12;

    MVBarcodeScanner.ScanningMode mMode = null;
    @MVBarcodeScanner.BarCodeFormat
    int[] mFormats = null;

    Barcode mBarcode;
    List<Barcode> mBarcodes;

    final static HashMap<Integer, String> TYPE_MAP;
    final static String[] barcodeTypeItems;

    static {
        TYPE_MAP = new HashMap<>();

        TYPE_MAP.put(Barcode.ALL_FORMATS, "All Formats");
        TYPE_MAP.put(Barcode.AZTEC, "Aztec");
        TYPE_MAP.put(Barcode.CALENDAR_EVENT, "Calendar Event");
        TYPE_MAP.put(Barcode.CODABAR, "Codabar");
        TYPE_MAP.put(Barcode.CODE_39, "Code 39");
        TYPE_MAP.put(Barcode.CODE_93, "Code 93");
        TYPE_MAP.put(Barcode.CODE_128, "Code 128");
        TYPE_MAP.put(Barcode.CONTACT_INFO, "Contact Info");
        TYPE_MAP.put(Barcode.DATA_MATRIX, "Data Matrix");
        TYPE_MAP.put(Barcode.DRIVER_LICENSE, "Drivers License");
        TYPE_MAP.put(Barcode.EAN_8, "EAN 8");
        TYPE_MAP.put(Barcode.EAN_13, "EAN 13");
        TYPE_MAP.put(Barcode.EMAIL, "Email");
        TYPE_MAP.put(Barcode.GEO, "Geo");
        TYPE_MAP.put(Barcode.ISBN, "ISBN");
        TYPE_MAP.put(Barcode.ITF, "ITF");
        TYPE_MAP.put(Barcode.PDF417, "PDF 417");
        TYPE_MAP.put(Barcode.PHONE, "Phone");
        TYPE_MAP.put(Barcode.QR_CODE, "QR Code");
        TYPE_MAP.put(Barcode.PRODUCT, "Product");
        TYPE_MAP.put(Barcode.SMS, "SMS");
        TYPE_MAP.put(Barcode.UPC_A, "UPC A");
        TYPE_MAP.put(Barcode.UPC_E, "UPC E");
        TYPE_MAP.put(Barcode.TEXT, "Text");
        TYPE_MAP.put(Barcode.URL, "URL");

        List<String> items = new ArrayList<>(TYPE_MAP.values());
        Collections.sort(items);
        String[] tempArray = new String[items.size()];
        tempArray = items.toArray(tempArray);
        barcodeTypeItems = tempArray;
    }

    Context context=this;
    myDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Display result
        result = (TextView) findViewById(R.id.result);
        result_tag = result.getText().toString();


        // Scan button
        scan = (Button) findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MVBarcodeScanner.Builder()
                        .setScanningMode(MVBarcodeScanner.ScanningMode.SINGLE_AUTO)
                        .setFormats(mFormats)
                        .build()
                        .launchScanner(MainActivity.this, REQ_CODE);
            }
        });

        list = (Button) findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK && data != null
                    && data.getExtras() != null) {
                Log.d("BARCODE-SCANNER", "onActivityResult inside block called");
                if (data.getExtras().containsKey(MVBarcodeScanner.BarcodeObject)) {
                    mBarcode = data.getParcelableExtra(MVBarcodeScanner.BarcodeObject);
                    mBarcodes = null;
                } else if (data.getExtras().containsKey(MVBarcodeScanner.BarcodeObjects)) {
                    mBarcodes = data.getParcelableArrayListExtra(MVBarcodeScanner.BarcodeObjects);
                    mBarcode = null;
                }
                updateBarcodeInfo();
            } else {
                mBarcode = null;
                mBarcodes = null;
                updateBarcodeInfo();
            }
        }
    }

    void updateBarcodeInfo() {
        StringBuilder builder = new StringBuilder();

        if (mBarcode != null) {
            Log.d("BARCODE-SCANNER", "got barcode");
            builder.append("Type: " + getBarcodeFormatName(mBarcode.format) +
                    "\nData: " + mBarcode.rawValue);
        }

        if (mBarcodes != null) {
            Log.d("BARCODE-SCANNER", "got barcodes");
            for (Barcode barcode : mBarcodes) {
                builder.append("Type: " + getBarcodeFormatName(barcode.format) +
                        "\nData: " + barcode.rawValue);
            }
        }

        if (builder.length() > 0) {
            result.setText(builder.toString());

            Random random = new Random();
            id = String.format("%04d", random.nextInt(10000));
            result_tag = result.getText().toString();
            myDbHelper=new myDbHelper(context);
            sqLiteDatabase = myDbHelper.getWritableDatabase();
            myDbHelper.addinnformation(id,result_tag,sqLiteDatabase);
            Toast.makeText(getBaseContext(),"Data saved", Toast.LENGTH_LONG).show();
            myDbHelper.close();

        }else {
            result.setText("");
        }
    }

    String getBarcodeFormatName(int format) {
        return TYPE_MAP.get(format);
    }

}

