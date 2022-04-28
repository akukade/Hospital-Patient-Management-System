package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fhospital.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Barcode extends AppCompatActivity {

    String TAG = "GenerateQRCODE";
    EditText qredit1;
    String inputvalue;
    Button start;
    ImageView qrimg;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode2);

        qrimg = findViewById(R.id.qrcode);
        qredit1 = findViewById(R.id.editqr);
        start = findViewById(R.id.qrgenerate);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputvalue = qredit1.getText().toString().trim();
                if(inputvalue.length() > 0){
                    WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerdimension = width<height ? width:height;
                    smallerdimension= smallerdimension *3/4;
                    qrgEncoder = new QRGEncoder(inputvalue, null, QRGContents.Type.TEXT, smallerdimension);
                    try {

                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrimg.setImageBitmap(bitmap);

                    }
                    catch (WriterException e){

                        Log.v(TAG,e.toString());
                    }

                }else{

                    qredit1.setError("Required");
                }


            }
        });


    }
}
