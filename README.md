<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
</head>
<body>
  <h1>📷 Barcode & QR‑Code Scanner</h1>
  <p>A mobile Android app using Google Mobile Vision API to scan various barcode formats and display/save results.</p>

  <hr>

  <h2>💡 What It Does</h2>
  <p>This app can scan and decode barcode types including:</p>
  <ul>
    <li>Aztec, Codabar, Code 39, Code 93, PDF 417, Data Matrix, QR Code</li>
    <li>EAN‑8, EAN‑13, UPC‑A, UPC‑E, ISBN, ITF</li>
    <li>Contact Info, Calendar Events, Drivers License, Geo, Email, Phone, SMS, Text, URL</li>
  </ul>
  <p>After scanning, results show in a list and can be deleted individually. Uses <strong>SINGLE_AUTO</strong> mode for fastest detection. :contentReference[oaicite:1]{index=1}</p>

  <hr>

  <h2>🛠️ How to Use</h2>
  <ol>
    <li>Clone the repo:<br><code>git clone https://github.com/devstoriesplayground/barcode-scanner.git</code></li>
    <li>Open in Android Studio.</li>
    <li>Build and run on a device/emulator with camera support.</li>
  </ol>

  <hr>

  <h2>📂 Project Structure</h2>
  <pre><code>barcode-scanner/
│
├── app/                        # Android application module (Java source, layouts, resources)
├── gradle/wrapper/            # Gradle wrapper files
├── .gitignore
├── BarcodeQrcodeScanner.iml   # Android Studio module file
├── build.gradle               # Project build script
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── settings.gradle
├── LICENSE?                   # (if included)
└── README.md                  # Original markdown README
</code></pre>

  <hr>

  <h2>📦 Dependencies</h2>
  <ul>
    <li>com.google.android.gms:play-services-vision</li>
    <li>online.devliving:mvbarcodereader:1.0.9</li>
    <li>Android support annotations</li>
  </ul>
  <p>Add them in your module’s <code>build.gradle</code>. :contentReference[oaicite:2]{index=2}</p>

  <hr>

  <h2>🔍 Scanning Modes</h2>
  <ul>
    <li><strong>SINGLE_AUTO:</strong> Fastest mode—automatically returns the first detected code.</li>
    <li><strong>SINGLE_MANUAL:</strong> Highlights all codes; user taps to select one.</li>
    <li><strong>MULTIPLE:</strong> Detects multiple codes; returns all on tap.</li>
  </ul>
  <p>This app currently uses <strong>SINGLE_AUTO</strong>. :contentReference[oaicite:3]{index=3}</p>

  <hr>

  <h2>🔗 Useful Links</h2>
  <ul>
    <li><a href="https://developers.google.com/vision/android/barcodes-overview" target="_blank">Google Mobile Vision Barcode API guide</a></li>
    <li><a href="https://github.com/Credntia/MVBarcodeReader" target="_blank">MVBarcodeReader (inspiration)</a></li>
  </ul>

  <hr>

  <h2>🙌 Contributing</h2>
  <p>Contributions are welcome! Fork the repo, improve features or add UI enhancements, and submit a pull request.</p>

  <hr>

  <h2>🧑‍💻 Author</h2>
  <p>Created and maintained by <strong>devstoriesplayground</strong> – feel free to reach out via GitHub!</p>

  <p>Happy coding, and enjoy learning Android Development! 🚀</p>

  <hr>

  <h2>📜 License</h2>
  <p>This project uses the <em>MIT License</em> (if applicable). Add a LICENSE file if it’s missing.</p>
</body>
</html>
