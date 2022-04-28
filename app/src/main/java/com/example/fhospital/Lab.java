package com.example.fhospital;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Lab extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText patupload, file;
    Button uploadbuton, choosebutton;
    Uri mimageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        patupload = findViewById(R.id.patidupload);
        file = findViewById(R.id.filenameuploaad);
        uploadbuton = findViewById(R.id.imguploadbtn);
        choosebutton = findViewById(R.id.imgchoosebtn);

        choosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        uploadbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void openFileChoose()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() !=null )
        {
            mimageUri = data.getData();

        }

    }
}
