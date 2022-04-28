package com.example.fhospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.UserWriteRecord;
import com.google.firebase.firestore.FirebaseFirestore;

public class Medhistory extends AppCompatActivity {

    TextView med1, med2, med3, med4, med5;
    FirebaseAuth fAuth;
    String userId;
    DatabaseReference reference1;
    FirebaseUser user;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medhistory);

        Bundle b = getIntent().getExtras();
        email = b.getString("email");
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        //System.out.println("emailid" + email);

        med1 = findViewById(R.id.medname);
        med2 = findViewById(R.id.medage);
        med3 = findViewById(R.id.medbg);
        med4 = findViewById(R.id.medgen);
        med5 = findViewById(R.id.medpresc);




        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("patients");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    if (post.child("email").getValue(String.class).equals(email)) {
                        med1.setText(post.child("fname").getValue(String.class));
                        med2.setText(post.child("age").getValue(String.class));
                        med3.setText(post.child("blood").getValue(String.class));
                        med4.setText(post.child("gender").getValue(String.class));
                        break;
                    }
                    //Toast.makeText(Medhistory.this, post.child("email").getValue(String.class), Toast.LENGTH_SHORT).show();
                    //System.out.println("emailid" + post.child("email").getValue(String.class));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference myref1 = FirebaseDatabase.getInstance().getReference("prescription");
        myref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                med5.setText(dataSnapshot.child(email.substring(0,email.length()-4)).getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
