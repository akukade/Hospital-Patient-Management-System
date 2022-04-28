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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
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
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import io.opencensus.tags.Tag;

public class PatientRegister extends AppCompatActivity {
    public static final String TAG = "TAG";
    RelativeLayout regpat1;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayout1;

    EditText mname, memail, mpassowrd, mage, mbg, mgen;
    LottieAnimationView lottiAnimationView;
    Button mregister;
    TextView mlogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    DatabaseReference reference;
    //FirebaseDatabase rootnode;
    String userId;
    //SignInButton signInButton;
    //GoogleSignInClient mGoogleSignInClient;
    //int RC_SIGN_IN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        mname = findViewById(R.id.name);
        regpat1 = findViewById(R.id.regpat);
        mage = findViewById(R.id.page);
        mbg = findViewById(R.id.pbg);
        memail = findViewById(R.id.email);
        mpassowrd = findViewById(R.id.password);
        mregister = findViewById(R.id.register);
        mlogin = findViewById(R.id.login);
        mgen = findViewById(R.id.pgen);
        //signInButton = findViewById(R.id.register2);
        lottiAnimationView = findViewById(R.id.animation1);
        relativeLayout = findViewById(R.id.innerRel);
        relativeLayout1 = findViewById(R.id.ImagRel);


        /*signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.register2:
                        signIn();
                        break;

                    // ...
                }
                Toast.makeText(PatientRegister.this, "User Created", Toast.LENGTH_SHORT).show();
            }
        });*/

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("patients");


        /*if(fAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),PatientRegister.class));
            finish();
        }*/


        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/




        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email1 = memail.getText().toString().trim();
                String pass = mpassowrd.getText().toString().trim();
                final String fullname1 = mname.getText().toString().trim();
                final String age1 = mage.getText().toString().trim();
                final String bg1 = mbg.getText().toString().trim();
                final String gen1 = mgen.getText().toString().trim();

                if(TextUtils.isEmpty(email1)){

                    memail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(pass)){

                    mpassowrd.setError("Password is Required.");
                    return;
                }

                if(pass.length() <6){

                    mpassowrd.setError("Password length must be greater than six characters");
                    return;
                }

                if(TextUtils.isEmpty(age1)){

                    mage.setError("Age is Required");
                    return;
                }

                if(TextUtils.isEmpty(bg1)){

                    mbg.setError("Blood Group is Required");
                    return;
                }

                if(TextUtils.isEmpty(gen1)){

                    mgen.setError("Gender is required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(PatientRegister.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();


                                }
                            });

                            //Toast.makeText(PatientRegister.this, "User Created", Toast.LENGTH_SHORT).show();
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("patients").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname", fullname1);
                            user.put("email", email1);
                            user.put("age",age1);
                            user.put("blood",bg1);
                            user.put("gender",gen1);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG, "onSuccess: user profile is created for" +userId);




                                }
                            });

                            userId = fAuth.getCurrentUser().getUid();
                            patient p = new patient(fullname1, email1,age1,bg1,gen1);
                            reference.child(userId).setValue(p);
                            //finish();

                            lottiAnimationView.setVisibility(View.VISIBLE);
                            relativeLayout1.setVisibility(View.GONE);
                            relativeLayout.setVisibility(View.GONE);
                            regpat1.setBackground(getDrawable(android.R.color.transparent));


                            lottiAnimationView.playAnimation();


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Intent intent;
                                    startActivity(new Intent(PatientRegister.this,Pdash1.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }, 4000);

                        }else{

                            Toast.makeText(PatientRegister.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),PatientLogin.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }


    /*private void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(PatientRegister.this,Pdash1.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }*/


}

