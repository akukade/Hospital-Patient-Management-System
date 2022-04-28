package com.example.fhospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class Med extends AppCompatActivity {

    EditText med1, med2;
    FirebaseAuth firebaseAuth;
    Button recod;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);

        med1 = findViewById(R.id.mename);
        med2 = findViewById(R.id.meemail);
        recod = findViewById(R.id.record);

        firebaseAuth = FirebaseAuth.getInstance();

        recod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(Med.this,Medhistory.class);
                a.putExtra("email",med2.getText().toString());
                startActivity(a);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

    }
}
