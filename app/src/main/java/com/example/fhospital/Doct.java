package com.example.fhospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Doct extends AppCompatActivity {

    TextView pname, pemail, verifymsg, paage, pagen;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    Button resendcode;
    DatabaseReference reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doct);

        pname = findViewById(R.id.docname);
        pemail = findViewById(R.id.docemail);
        pagen = findViewById(R.id.docgen);
        paage = findViewById(R.id.docage);

        resendcode = findViewById(R.id.docverify);
        verifymsg = findViewById(R.id.docver);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        final FirebaseUser user = fAuth.getCurrentUser();

        if(!user.isEmailVerified()){

            //verifymsg.setVisibility(View.VISIBLE);
            //resendcode.setVisibility(View.VISIBLE);

            /*resendcode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {


                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(v.getContext(), "Verification email has been sent", Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            });*/
        }

        reference1 = FirebaseDatabase.getInstance().getReference("doctors").child(userId);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                docotor d  = dataSnapshot.getValue(docotor.class);
                pname.setText(d.getFname());
                pemail.setText(d.getEmail());
                paage.setText(d.getAge());
                pagen.setText(d.getGender());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {






            }
        });




        /*DocumentReference documentReference = fStore.collection("doctors").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                pname.setText(documentSnapshot.getString("fname"));
                pemail.setText(documentSnapshot.getString("email"));
                paage.setText(documentSnapshot.getString("age"));
                pagen.setText(documentSnapshot.getString("gender"));

            }
        });*/


    }
}
