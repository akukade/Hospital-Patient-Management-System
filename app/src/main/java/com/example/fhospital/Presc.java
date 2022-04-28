package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class Presc extends AppCompatActivity {

    EditText et, pat1;
    Button upbtn;
    FirebaseFirestore fstore;
    FirebaseAuth firebaseAuth;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presc);


        et = findViewById(R.id.type);
        upbtn = findViewById(R.id.upload);
        pat1 = findViewById(R.id.patidpresc);

        fstore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inal String pre = et.getText().toString().trim();
                //userid = firebaseAuth.getCurrentUser().getUid();
                //DocumentReference documentReference = fstore.collection("prescription").document(userid);
                //Map<String,Object> user = new HashMap<>();
                //user.put("presc", pre);
                DatabaseReference myref = FirebaseDatabase.getInstance().getReference("prescription");
                String email = pat1.getText().toString();
                email = email.substring(0, email.length()-4);
                myref.child(email).setValue(et.getText().toString());

            }

        });

    }
}
