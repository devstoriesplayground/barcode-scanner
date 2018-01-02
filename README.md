Mobile Barcode Scanner using the Google Play services, mobile vision api.

This application reads different kinds of barcode format such as:

1. Aztec
2. Calendar Event
3. Codabar
4. Code 39
5. Code 93
6. Contact Info
7. Data Matrix
8. Drivers Lisence
9. EAN 8
10. EAN 13
11. Email
12. Geo
13. ISBN
14. ITF
15. PDF 417
16. Phone
17. QR COde
18. SMS
19. UPC A
20. UPC E
21. Text
22. URL

HOW TO USE:
Scan the barcode after the application reads the scanned objject the result will be displayed in the activity form. It also saves results per scan object. The user can also delete one scan object at a time. 

SCANNING MODE:
* SINGLE_AUTO: The fastest mode. Returns the first barcode it can detect as soon as possible.
* SINGLE_MANUAL: Detects and highlights all the barcode it can find but returns only the one that user chooses by tapping
* MULTIPLE: Detects and highlights all the barcode it can find. Returns all the barcodes on tap.

In this application, I only choose SINGLE_AUTO scanning mode for faster display of result.

USAGE FOR DEVELOPERS 
For GRADLE: <Copy and Paste the following codes>
  
dependencies 
   * compile 'online.devliving:mvbarcodereader:1.0.9'
   * compile 'com.google.android.gms:play-services-basement:11.0.1'
   * compile 'com.google.android.gms:play-services-vision:11.0.1'
   * compile 'com.android.support:support-annotations:25.3.1'


For more information about mobile vision api, please follow this link.
   * https://developers.google.com/vision/android/barcodes-overview
    
Special thanks for this project.
   * https://github.com/Credntia/MVBarcodeReader
  
