package com.example.ddpaytmfunda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPactivity extends AppCompatActivity {
    private String verificationID;
    private FirebaseAuth mAuth;
    private EditText entredotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_pactivity);
        mAuth=FirebaseAuth.getInstance();
        entredotp=findViewById(R.id.enterOTP);

        String phonenumber=getIntent().getStringExtra("phonenumber");
         sendVerificationcode(phonenumber);

         findViewById(R.id.loginotpBtn).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String code=entredotp.getText().toString().trim();
                 if (code.isEmpty()||code.length()<6){
                     entredotp.setError("Enter OTP");
                     entredotp.requestFocus();
                     return;

                 }
                 verifyOtp(code);

             }
         });


    }

    private void verifyOtp(String code){

        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationID,code);
        signInwithCredential(credential);
    }

    private void signInwithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(OTPactivity.this,MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }else {
                    Toast.makeText(OTPactivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void sendVerificationcode(String number){

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
 private PhoneAuthProvider.OnVerificationStateChangedCallbacks
         mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
     @Override
     public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
         super.onCodeSent(s, forceResendingToken);
         verificationID=s;
     }

     @Override
     public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
         String code=phoneAuthCredential.getSmsCode();
         if (code!=null)
           entredotp.setText(code);
             verifyOtp(code);

     }

     @Override
     public void onVerificationFailed(@NonNull FirebaseException e) {
         Toast.makeText(OTPactivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

     }
 };



}
