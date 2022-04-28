package com.example.fhospital;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Maps extends AppCompatActivity {

    Button btnmapsearch;
    EditText edtsearch;
    TextView textView;
    ImageView imageView;
    LottieAnimationView lottieAnimationView;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnmapsearch = findViewById(R.id.btnmapsearch);
        edtsearch=(EditText)findViewById(R.id.edtsearch);
        lottieAnimationView = findViewById(R.id.animation1);
        textView = findViewById(R.id.TextView5);
        imageView = findViewById(R.id.ImageViewMap);
        relativeLayout = findViewById(R.id.LinMap);


        btnmapsearch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+edtsearch.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);



                /*lottieAnimationView.setVisibility(View.VISIBLE);

                btnmapsearch.setVisibility(View.GONE);
                edtsearch.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                relativeLayout.setBackground(getDrawable(android.R.color.transparent));*/



                //lottieAnimationView.playAnimation()


            }
        });



    }
}
