package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signupSecondClass extends AppCompatActivity {


    RadioGroup radigroup;
    RadioButton radiobutton;

    String fullname,username,email,password,confirmpassword;

    Button buttonNextSecond,buttonLoginSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_second_class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        buttonLoginSecond=findViewById(R.id.buttonLoginSecond);
        buttonNextSecond=findViewById(R.id.buttonNextSecond);
        radigroup=findViewById(R.id.radio_group);
    }

    public void setButtonNextSecond(View view)
    {

        if(!validateGender())
        {
            return;
        }else
        {

            fullname= getIntent().getStringExtra("fullname");
            username= getIntent().getStringExtra("username");
            email= getIntent().getStringExtra("email");
            password= getIntent().getStringExtra("password");
            confirmpassword= getIntent().getStringExtra("confirmpassword");


            radiobutton=findViewById(radigroup.getCheckedRadioButtonId());
            String gender=radiobutton.getText().toString();

            Intent intent=new Intent(this,signupThirdAge.class);
            intent.putExtra("fullname", fullname);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            intent.putExtra("confirmpassword", confirmpassword);
            intent.putExtra("gender",gender);

            startActivity(intent);
            finish();
        }



    }

    private boolean validateGender(){
        if(radigroup.getCheckedRadioButtonId()== -1)
        {
            Toast.makeText(this,"select your gender",Toast.LENGTH_SHORT).show();
            return false;
        }else
        {
            return true;
        }
    }
















    public void setButtonLoginSecond(View view)
    {
        startActivity(new Intent(this,signup.class));
        finish();
    }
}
