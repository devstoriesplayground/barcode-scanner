package com.example.cdarroyo.barcodeqrcodescanner;

/**
 * Created by cdarroyo on 12/20/2017.
 */

public class Result {
    String id, scan;


    public Result(String id, String scan) {
        this.id = id;
        this.scan = scan;
    }

    public String getid() {
        return id;
    }

    public String getScan() {
        return scan;
    }

}
