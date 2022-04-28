package com.example.fhospital;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DoctorRegister extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText doname, doemail, dopassowrd, doage, dogen;
    Button doregister;
    TextView dologin;
    FirebaseAuth fAuthm;
    FirebaseFirestore fstore;
    String userId;
    DatabaseReference ref;
    LottieAnimationView lottieAnimationView;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayout1;
    RelativeLayout relativeLayout2;
    RelativeLayout relativeLayout3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        doname = findViewById(R.id.dname);
        doemail = findViewById(R.id.demail);
        dopassowrd = findViewById(R.id.dpassword);
        doregister = findViewById(R.id.dregister);
        dologin = findViewById(R.id.dlog);
        doage = findViewById(R.id.dage);
        dogen = findViewById(R.id.dgen);
        lottieAnimationView = findViewById(R.id.animation1);
        relativeLayout = findViewById(R.id.OutRel);
        relativeLayout1 = findViewById(R.id.InnerRel2);
        relativeLayout2 = findViewById(R.id.InnerRel3);
        relativeLayout3 = findViewById(R.id.InnerRel4);

        fAuthm = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("doctors");

        /*if(fAuthm.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),DoctorRegister.class));
            finish();
        }*/

        doregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailid = doemail.getText().toString().trim();
                String pass1 = dopassowrd.getText().toString().trim();
                final String fullname = doname.getText().toString().trim();
                final String dage1 = doage.getText().toString().trim();
                final String dgen = dogen.getText().toString().trim();

                if(TextUtils.isEmpty(emailid)){

                    doemail.setError("Email is Requird.");
                    return;
                }
                if(TextUtils.isEmpty(pass1)){

                    dopassowrd.setError("Password is Required.");
                    return;
                }

                if(pass1.length() <6){

                    dopassowrd.setError("Password length must be greater than six characters");
                    return;
                }

                fAuthm.createUserWithEmailAndPassword(emailid,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser fuser = fAuthm.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(DoctorRegister.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();

                                }
                            });


                            Toast.makeText(DoctorRegister.this, "User Created", Toast.LENGTH_SHORT).show();
                            userId = fAuthm.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("doctors").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname", fullname);
                            user.put("email", emailid);
                            user.put("age",dage1);
                            user.put("gender",dgen);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG, "onSuccess: user profile is created for" +userId);

                                }
                            });


                            userId = fAuthm.getCurrentUser().getUid();
                            docotor d = new docotor(fullname, emailid, dage1, dgen);
                            ref.child(userId).setValue(d);
                            //finish();

                            lottieAnimationView.setVisibility(View.VISIBLE);

                            relativeLayout1.setVisibility(View.GONE);
                            relativeLayout2.setVisibility(View.GONE);
                            relativeLayout3.setVisibility(View.GONE);
                            relativeLayout.setBackground(getDrawable(android.R.color.transparent));


                            lottieAnimationView.playAnimation();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Intent intent;
                                    startActivity(new Intent(DoctorRegister.this,Docdash2.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }, 4000);



                        }else{

                            Toast.makeText(DoctorRegister.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

        dologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),DoctorLogin.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}

