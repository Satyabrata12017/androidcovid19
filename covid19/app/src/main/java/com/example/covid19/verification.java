package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class verification extends AppCompatActivity {

    PinView pinView;
    String codeBySystem;
    Button buttonVerify;

    String fullname,username,email,password,confirmpassword,gender,age,phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pinView=findViewById(R.id.pin_view);


        fullname= getIntent().getStringExtra("fullname");
        username= getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        confirmpassword =getIntent().getStringExtra("confirmpassword");
        gender= getIntent().getStringExtra("gender");
        age=getIntent().getStringExtra("age");
        phoneno=getIntent().getStringExtra("phoneno");

        System.out.println(fullname);
        System.out.println(username);

        sendaVerificationCOdeToUser(phoneno);
        buttonVerify=findViewById(R.id.button_verify);


    }

    private void sendaVerificationCOdeToUser(String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem=s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    String code=phoneAuthCredential.getSmsCode();
                    if(code!=null)
                    {
                        pinView.setText(code);
                        verifyCOde(code);
                    }

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {

                    Toast.makeText(verification.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            };

    private void verifyCOde(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //storeNewUserData();

                            startActivity(new Intent(verification.this,verificationcomplete.class));
                            finish();
                            Toast.makeText(verification.this,"verification is completed.",Toast.LENGTH_SHORT).show();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(verification.this,"verification is not completed. please verify your phone",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    /*private void storeNewUserData() {
        FirebaseDatabase rootnode= FirebaseDatabase.getInstance();
        DatabaseReference reference=rootnode.getReference("user");
        userHelperClass addnewUser=new userHelperClass(fullname,username,email,password,confirmpassword,gender,age,phoneno);
        reference.setValue(addnewUser);




    }*/
    public void setButtonVerify(View view) {

        String code=pinView.getText().toString();

        if(!code.isEmpty())
        {
            verifyCOde(code);
        }

    }
}
