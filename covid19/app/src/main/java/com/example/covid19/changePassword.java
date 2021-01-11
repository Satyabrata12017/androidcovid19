package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class changePassword extends AppCompatActivity {


    EditText newPassword,confirmPassword;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        newPassword=findViewById(R.id.new_password);
        confirmPassword=findViewById(R.id.confirm_password);
        ok=findViewById(R.id.ok);

    }

    public void setOk(View view)
    {
        startActivity(new Intent(this,updateConfirm.class));
        finish();
    }
}
