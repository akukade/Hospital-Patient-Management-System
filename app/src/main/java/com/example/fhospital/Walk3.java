package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Walk3 extends AppCompatActivity {

    Animation smalltobig, nottocome, btnanm;
    ImageView iv;
    TextView tv1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk3);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smltobig);
        nottocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome);
        btnanm = AnimationUtils.loadAnimation(this,R.anim.btnanim);

        iv = findViewById(R.id.walk3);
        tv1 = findViewById(R.id.fitext);
        b1 = findViewById(R.id.cont2);

        iv.startAnimation(smalltobig);
        tv1.startAnimation(nottocome);
        b1.startAnimation(btnanm);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(Walk3.this,Main2.class);
                startActivity(a);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }
}
