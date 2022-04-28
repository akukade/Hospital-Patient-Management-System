package com.example.fhospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorLogin extends AppCompatActivity {

    EditText pemail, mpassword;
    Button logbtn;
    TextView reg1;

    FirebaseAuth fAuthp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        pemail = findViewById(R.id.docloge);
        mpassword = findViewById(R.id.doclogp);
        fAuthp = FirebaseAuth.getInstance();
        logbtn = findViewById(R.id.doclogin);
        reg1 = findViewById(R.id.docreg);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = pemail.getText().toString().trim();
                String pass = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email1)){

                    pemail.setError("Email is Requird.");
                    return;
                }
                if(TextUtils.isEmpty(pass)){

                    mpassword.setError("Password is Required.");
                    return;
                }

                if(pass.length() <6){

                    mpassword.setError("Password length must be greter than six characters");
                    return;
                }

                fAuthp.signInWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(DoctorLogin.this, "Logged In Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Docdash2.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        }else
                        {

                            Toast.makeText(DoctorLogin.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),DoctorRegister.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}
