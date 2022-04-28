package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Emergency extends AppCompatActivity {
    EditText em1,em2,em3;
    Button upload;
    String userId;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        em1 = findViewById(R.id.emer1);
        em2 = findViewById(R.id.emer2);
        em3 = findViewById(R.id.emer3);
        upload = findViewById(R.id.emerbtn1);

        fstore = FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("emergency");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emergency1 = em1.getText().toString().trim();
                final String emergency2 = em2.getText().toString().trim();
                final String emergency3 = em3.getText().toString().trim();

                if(TextUtils.isEmpty(emergency1)){

                    em1.setError("Field is Empty");
                    return;
                }

                if(TextUtils.isEmpty(emergency2)){

                    em2.setError("Field is Empty");
                    return;
                }

                if(TextUtils.isEmpty(emergency3)){

                    em3.setError("Field is Empty");
                    return;
                }

                DocumentReference documentReference = fstore.collection("emergency").document(userId);
                Map<String,Object> user = new HashMap<>();
                user.put("number1", emergency1);
                user.put("number2", emergency2);
                user.put("number3",emergency3);

            }
        });


    }
}
