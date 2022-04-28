package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2 extends AppCompatActivity {

    CardView pat1, doct1, lab, emergency;
    TextView wel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pat1 = findViewById(R.id.pbtn);
        doct1 = findViewById(R.id.dbtn);
        wel = findViewById(R.id.welcome);
        //lab = findViewById(R.id.labbtn);
        emergency = findViewById(R.id.emerbtn);


        pat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PatientRegister.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        doct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DoctorRegister.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        //wel.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //startActivity(new Intent(getApplicationContext(),Card.class));
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //}
        //});

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Emergency.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        //lab.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //startActivity(new Intent(getApplicationContext(),Lab.class));
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //}
        //});


    }
}
