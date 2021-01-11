package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class signupFourPhone extends AppCompatActivity {


    CountryCodePicker countryCodePicker;
    EditText phonenumber;
    FirebaseAuth auth;
    Intent intent;

    String fullname,username,email,password,confirmpassword,gender,age,phoneno;



    Button buttonNextFour,buttonBackFour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_four_phone);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        buttonBackFour=findViewById(R.id.buttonbackFour);
        buttonNextFour=findViewById(R.id.buttonNextFour);
        countryCodePicker=findViewById(R.id.countryCodePicker);
        phonenumber=findViewById(R.id.phoneNumber);

        auth=FirebaseAuth.getInstance();


    }
    public void setButtonNextFour(View view)
    {
        if(!validatePhonenumber())
        {
            return;
        }else
        {
            fullname= getIntent().getStringExtra("fullname");
            username= getIntent().getStringExtra("username");
            email = getIntent().getStringExtra("email");
            password = getIntent().getStringExtra("password");
            confirmpassword =getIntent().getStringExtra("confirmpassword");
            gender= getIntent().getStringExtra("gender");
            age=getIntent().getStringExtra("age");

            System.out.println(fullname);
            System.out.println(username);
            System.out.println(email);


            String _phonenumber=phonenumber.getText().toString();
            phoneno="+"+countryCodePicker.getSelectedCountryCode()+_phonenumber;

            intent=new Intent(getApplicationContext(),verification.class);
            intent.putExtra("fullame",fullname);
            intent.putExtra("userame",username);
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            intent.putExtra("confirmpassword",confirmpassword);
            intent.putExtra("gender",gender);
            intent.putExtra("age",age);
            intent.putExtra("phoneno",phoneno);

           // storeNewUserData();

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(signupFourPhone.this, "user created", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }else
                    {
                        Toast.makeText(signupFourPhone.this, "Error occured"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });





        }


    }

   /* private void storeNewUserData() {

        FirebaseDatabase rootnode= FirebaseDatabase.getInstance();
        DatabaseReference reference=rootnode.getReference("user");


        userHelperClass addnewUser=new userHelperClass(fullname,username,email,password,confirmpassword,gender,age,phoneno);
        reference.setValue(addnewUser);
    }*/


    private boolean validatePhonenumber(){
        String phoneNumber=phonenumber.getText().toString();

        if(phoneNumber.isEmpty())
        {
            phonenumber.setError("Field can not be empty");
            return false;
        }else if (phoneNumber.length()>10)
        {
            phonenumber.setError("number is not correct");
            return false;

        }
        else
        {
            phonenumber.setError(null);
            return true;
        }

    }












    public void setButtonBackFour(View view)
    {
        startActivity(new Intent(this,signupThirdAge.class));
        finish();
    }
}
