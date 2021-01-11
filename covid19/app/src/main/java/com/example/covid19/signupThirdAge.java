package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class signupThirdAge extends AppCompatActivity {

    Button nextButtonThird,backButtonThird;

    DatePicker datePicker;

    String fullname,username,email,password,confirmpassword,gender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third_age);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nextButtonThird=findViewById(R.id.buttonNextThird);
        backButtonThird=findViewById(R.id.buttonBack);
        datePicker=findViewById(R.id.select_age);






    }

    public void setBackButtonThird(View view)
    {
        startActivity(new Intent(this,signupSecondClass.class));
        finish();
    }



    public void setNextButtonThird(View view)
    {

        if(!validateAge())
        {
            return;
        }else
        {

            fullname= getIntent().getStringExtra("fullname");
            username= getIntent().getStringExtra("username");
            email= getIntent().getStringExtra("email");
            password= getIntent().getStringExtra("password");
            confirmpassword= getIntent().getStringExtra("confirmpassword");
            gender=getIntent().getStringExtra("gender");

            System.out.println(fullname);
            System.out.println(username);

            int day=datePicker.getDayOfMonth();
            int month=datePicker.getMonth();
            int year=datePicker.getYear();

            String age= day+"/"+month+"/"+year;

            Intent intent=new Intent(this,signupFourPhone.class);

            intent.putExtra("fullname", fullname);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            intent.putExtra("confirmpassword", confirmpassword);
            intent.putExtra("gender",gender);
            intent.putExtra("age",age);

            startActivity(intent);
            finish();
        }

    }

    private boolean validateAge(){
        int currentyear= Calendar.getInstance().get(Calendar.YEAR);
        int userAge=datePicker.getYear();
        int checkAge=currentyear-userAge;

        if(checkAge<14)
        {
            Toast.makeText(this,"not allowed to signup",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }

    }
}
