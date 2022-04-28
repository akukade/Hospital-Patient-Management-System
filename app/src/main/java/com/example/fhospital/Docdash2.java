package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fhospital.R;

public class Docdash2 extends AppCompatActivity {

    //GridLayout mainGrid;
    LinearLayout mainlin;
    Animation smalltobig;
    CardView prof1,pred1, idead, pre, his;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docdash2);

        prof1 = findViewById(R.id.profd);
        pred1 = findViewById(R.id.predictiond);
        idead = findViewById(R.id.idead);
        pre = findViewById(R.id.prescd);
        his = findViewById(R.id.historyd);
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smltobig);
        mainlin = findViewById(R.id.mainlinear);
        //mainGrid = findViewById(R.id.grid1);
        mainlin.startAnimation(smalltobig);

        //setSingleEvent(mainGrid);

        //setToggleEvent(mainGrid);



        prof1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Doct.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        pred1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Pred.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        idead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Maps.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Presc.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Med.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });


    }

    /*private void setToggleEvent(GridLayout mainGrid) {

        for (int i=0;i<mainGrid.getChildCount();i++)
        {
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1)
                    {
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                    }
                    else
                    {
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    }

                }
            });
        }

    }

    private void setSingleEvent(GridLayout mainGrid) {

        for(int i=0; i<mainGrid.getChildCount();i++)
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(getApplicationContext(),Doct.class));

                }
            });
        }
    }*/
}