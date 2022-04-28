package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fhospital.R;

public class Pred1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pred1);


        Intent intent = getIntent();

        String result = intent.getStringExtra("EXTRA_MESSAGE");
        //System.out.println(result);
        //System.out.println("dncdjsnchbdhsbdasbdjasdbajsbdajsdbasjdbajkdb"+result);
        TextView textView = findViewById(R.id.predtext);
        textView.setText(result);


    }
}
