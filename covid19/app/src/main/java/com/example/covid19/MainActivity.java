package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPALH_SCREEN=5000;

    Animation topanim,bottomanim;

    ImageView image1,image2;
    SharedPreferences onBoardingScreen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image1=findViewById(R.id.imageview);
        image2=findViewById(R.id.image2);

        image1.setAnimation(topanim);
        image2.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen=getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if (isFirstTime)
                {
                    SharedPreferences.Editor editor=onBoardingScreen.edit();
                    editor.putBoolean("firsTime",false);
                    editor.commit();

                    Intent intent=new Intent(MainActivity.this,onBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent=new Intent(MainActivity.this,Dashboardpage.class);
                    startActivity(intent);
                    finish();
                }


            }
        },SPALH_SCREEN);
    }
}
