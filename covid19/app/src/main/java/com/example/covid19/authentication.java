package com.example.covid19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class authentication extends AppCompatActivity {

    Button login,signup,skip,buttonLanguage;
    TextView doitLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadLocale();
        login=findViewById(R.id.button_login);
        signup=findViewById(R.id.button_signup);
        skip=findViewById(R.id.skip_button);
        doitLater=findViewById(R.id.textview);
        buttonLanguage=findViewById(R.id.buttonLanguage);
        buttonLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });


    }

    private void showChangeLanguageDialog() {

        final String[] listItems = {"French","Hindi","Telugu","Chinese","English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(i==0)
                {
                    setLocale("fr");
                    recreate();
                }else if (i==1)
                {
                    setLocale("hi");
                    recreate();
                }else if (i==2)
                {
                    setLocale("te");
                    recreate();
                }else if (i==3)
                {
                    setLocale("zh-rCN");
                    recreate();
                }else if (i==4)
                {
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();

            }
        });

        AlertDialog mDialog=mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("MY_LANG",lang);
        editor.apply();


    }
    public void loadLocale(){
        SharedPreferences prefs=getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language=prefs.getString("MY_LANG","");
        setLocale(language);
    }

    public void skipmethod(View view){
        startActivity(new Intent(this,Dashboardpage.class));
        finish();
    }
    public void setDoitLater(View view){
        startActivity(new Intent(this,Dashboardpage.class));
        finish();

    }
    public void setLogin(View view){
        startActivity(new Intent(this,login.class));
        finish();

    }
    public void setSignup(View view){
        startActivity(new Intent(this,signup.class));
        finish();

    }


}
