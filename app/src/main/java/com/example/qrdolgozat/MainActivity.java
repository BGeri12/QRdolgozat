package com.example.qrdolgozat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btn_scan,btn_kiir;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("QRCode Szkenn");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();

            }
        });

        btn_kiir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Kiir.kiir(tv);
                } catch (IOException e) {
                    Log.d("Kiirasi problema",e.getMessage());
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null)
        {
            Toast.makeText(this, "Kiléptél a Scanből", Toast.LENGTH_SHORT).show();
        } else {
            tv.setText("Kiolvasott feladat:" + result.getContents());

            Uri uri = Uri.parse(result.getContents());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {

        btn_scan = findViewById(R.id.buttonScan);
        btn_kiir = findViewById(R.id.buttonKiir);
        tv = findViewById(R.id.textView);
    }
}