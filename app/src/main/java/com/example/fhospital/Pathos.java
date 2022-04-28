package com.example.fhospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
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

public class Pathos extends AppCompatActivity {

    TextView pname,pemail, verifymsg, pabg, paage, pagen;
    ImageView propic;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    Button resendcode;
    DatabaseReference reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathos);

        pname = findViewById(R.id.proname);
        paage = findViewById(R.id.proage);
        pabg = findViewById(R.id.probg);
        pemail = findViewById(R.id.proemail);
        resendcode = findViewById(R.id.verify);
        verifymsg = findViewById(R.id.ver);
        pagen = findViewById(R.id.progen);
        propic = findViewById(R.id.profilepic);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        final FirebaseUser user = fAuth.getCurrentUser();



        /*GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            pname.setText(personName);
            pemail.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(propic);

        }*/





        if(!user.isEmailVerified()){

            verifymsg.setVisibility(View.VISIBLE);
            resendcode.setVisibility(View.VISIBLE);

            resendcode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {


                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(v.getContext(), "Verification email has been sent", Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            });
        }


        /*final DocumentReference documentReference = fStore.collection("patients").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                pname.setText(documentSnapshot.getString("fname"));
                pemail.setText(documentSnapshot.getString("email"));
                paage.setText(documentSnapshot.getString("age"));
                pabg.setText(documentSnapshot.getString("blood"));
                pagen.setText(documentSnapshot.getString("gender"));


            }
        });*/

        reference1 = FirebaseDatabase.getInstance().getReference("patients").child(userId);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                patient p = dataSnapshot.getValue(patient.class);
                pname.setText(p.getFname());
                pemail.setText(p.getEmail());
                paage.setText(p.getAge());
                pabg.setText(p.getBlood());
                pagen.setText(p.getGender());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
