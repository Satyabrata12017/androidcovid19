package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

public class signup extends AppCompatActivity {

    Button backSignup,login,next;
    EditText textFullname,textUsername,textEmail;
    ShowHidePasswordEditText passwordConfirm,passwordEnter;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backSignup=findViewById(R.id.buttonBackSignup);
        login=findViewById(R.id.buttonLogin);
        next=findViewById(R.id.buttonNext);
        textFullname=findViewById(R.id.editTextFullName);
        textUsername=findViewById(R.id.editTextUserName);
        textEmail=findViewById(R.id.editTextEmail);
        passwordEnter=findViewById(R.id.password);
        passwordConfirm=findViewById(R.id.confirmpassword);


    }

    public void setBackSignup(View view)
    {
        startActivity(new Intent(this,authentication.class));
        finish();
    }



    public void setNext(View view)
    {

        if(!validateFullname() | !validateUserName() | !validateEmail() | !validatePassword() | !validateConfirmPassword())
        {
            return;
        }
        else
        {
            String fullname=textFullname.getText().toString();
            String username=textUsername.getText().toString();
            String email=textEmail.getText().toString();
            String password=passwordEnter.getText().toString();
            String confirmpassword=passwordConfirm.getText().toString();

            System.out.println(fullname);
            System.out.println(username);

            Intent intent = new Intent(this,signupSecondClass.class);
            intent.putExtra("fullname", fullname);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            intent.putExtra("confirmpassword", confirmpassword);

            startActivity(intent);
            finish();
        }


    }








    private boolean validateFullname()
    {
        String fullName=textFullname.getText().toString().trim();

        if(fullName.isEmpty())
        {
            textFullname.setError("Field can not be empty");
            return false;

        }else
        {
            textFullname.setError(null);
            return true;
        }
    }

    private boolean validateUserName()
    {
        String username=textUsername.getText().toString().trim();
        //String checkSpaces="\\A\\W{1,20}\\Z";

        if(username.isEmpty())
        {
            textUsername.setError("Field can not be empty");
            return false;

        }else if(username.length()>20)
        {
            textUsername.setError("user name is too bigger");
            return false;

        }/*else if(!username.matches(checkSpaces)) {
            textUsername.setError("no white spaces are allowed");
            return false;

        }*/

        else
        {
            textUsername.setError(null);
            return true;
        }
    }



    private boolean validateEmail()
    {
        String email=textEmail.getText().toString();
        String spaces="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(email.isEmpty())
        {
            textEmail.setError("Field can not be empty");
            return false;

        }else if(email.length()>100)
        {
            textEmail.setError("Email id is too bigger");
            return false;

        }else if(!email.matches(spaces)) {
            textEmail.setError("Invalid Email !");
            return false;

        }

        else
        {
            textEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword()
    {
        password= passwordEnter.getText().toString();
        //String spacespassword="^(?=.*[A-Za-z])(?=.*\\\\\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\\\\\d$@$!%*#?&]{8,}$";

        if(password.isEmpty())
        {
            passwordEnter.setError("Field can not be empty");
            return false;

        }else if(password.length()>20)
        {
            passwordEnter.setError("password is too big");
            return false;

        }/*else if(!password.matches(spacespassword)) {
            passwordEnter.setError("password shoud contail uppercase,lowercase,number,special character");
            return false;

        }*/

        else
        {
            passwordEnter.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword()
    {
        String passwordC= passwordConfirm.getText().toString();


        if(passwordC.isEmpty())
        {
            passwordConfirm.setError("Field can not be empty");
            return false;

        }else if(passwordC.length()>20)
        {
            passwordConfirm.setError("password is too big ");
            return false;

        }else if(!passwordC.matches(password)) {
            passwordConfirm.setError("password not matched !");
            return false;

        }

        else
        {
            passwordConfirm.setError(null);
            return true;
        }
    }


    public void setLogin(View view)
    {
        startActivity(new Intent(this,login.class));
        finish();
    }
}
