package com.example.ddpaytmfunda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText phonenumb;
    private TextView Textview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Textview1=findViewById(R.id.textView1);
        phonenumb=findViewById(R.id.phonenum);
        findViewById(R.id.nextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=phonenumb.getText().toString().trim();
                if (number.isEmpty()||number.length()>10){
                    phonenumb.setError("Valid Number is Required");
                    phonenumb.requestFocus();

                    return;

                }
                String phoneNumber="+" + 91 + number;
                Intent i =new Intent(LoginActivity.this,OTPactivity.class);
                i.putExtra("phonenumber",phoneNumber);
                startActivity(i);
                finishAffinity();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
    }
}