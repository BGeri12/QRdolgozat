package com.example.qrdolgozat;

import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Kiir {

    public static void kiir(TextView tv) throws IOException {

        Date datum = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formazottDatum =dateFormat.format(datum);

        String szoveg = String.format("%s,%s",tv,formazottDatum);

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            File file = new File(Environment.getExternalStorageDirectory(),"scannedCodes.csv");

            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.append(szoveg);
            bw.append(System.lineSeparator());
            bw.close();
        }
    }
}