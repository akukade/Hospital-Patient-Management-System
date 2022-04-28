package com.example.fhospital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ideadash extends AppCompatActivity {

    LinearLayout idea1,idea2,idea3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideadash);

        idea1 = findViewById(R.id.idea11);
        idea2 = findViewById(R.id.idea12);
        idea3 =findViewById(R.id.idea13);


    }
}

