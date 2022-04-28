package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Walk extends AppCompatActivity {

    Animation smalltobig, nottocome, btnanm;
    ImageView iv;
    TextView tv1;
    Button b1,b2;
    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);


        nottocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome);
        btnanm = AnimationUtils.loadAnimation(this,R.anim.btnanim);
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smltobig);

        iv = findViewById(R.id.walk1);
        tv1 = findViewById(R.id.nfctext);
        b1 = findViewById(R.id.cont);
        b2 = findViewById(R.id.skip);

        //iv.startAnimation(smalltobig);
        tv1.startAnimation(nottocome);
        b1.startAnimation(btnanm);
        b2.startAnimation(smalltobig);

        // nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //if(nfcAdapter!=null && nfcAdapter.isEnabled()){

        //Toast.makeText(this,"NFC available", Toast.LENGTH_SHORT).show();;
        //}else{
        //finish();
        //Toast.makeText(this, "NFC not available", Toast.LENGTH_SHORT).show();
        //}




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Walk.this,Walk2.class);
                startActivity(a);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(Walk.this,Main2.class);
                startActivity(a);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    //@Override
    //protected void onNewIntent(Intent intent) {

    // Toast.makeText(this, "NFC Intent Received", Toast.LENGTH_LONG).show();
    //super.onNewIntent(intent);


    //}

    //@Override
    //protected void onResume() {

    //Intent intent = new Intent(this, Walk.class);
    //intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
    //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    //IntentFilter[] intentFilter = new IntentFilter[]{};
    //nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    //super.onResume();
    //}

    //@Override
    //protected void onPause() {
    //nfcAdapter.disableForegroundDispatch(this);

    //super.onPause();
    //}
}


