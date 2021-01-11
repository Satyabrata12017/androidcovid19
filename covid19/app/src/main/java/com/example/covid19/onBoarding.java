package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class onBoarding extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dots;
    SliderAdapter sliderAdapter;

    TextView[] dots_layout;
    Button letsGetStarted;
    Animation animation;

    int currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewPager=findViewById(R.id.slider);
        dots=findViewById(R.id.dots);
        letsGetStarted=findViewById(R.id.get_started_button);

        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);



        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

    }

    public void getStarted(View view)
    {
        startActivity(new Intent(this,authentication.class));
        finish();
    }

    public void skip(View view)
    {
        startActivity(new Intent(this,authentication.class));
        finish();

    }
    public void next(View view)
    {
        viewPager.setCurrentItem(currentPos+1);

    }

    private void addDots(int position){
        dots_layout= new TextView[4];
        dots.removeAllViews();
        for(int i=0;i<dots_layout.length;i++)
        {
            dots_layout[i]=new TextView(this);
            dots_layout[i].setText(Html.fromHtml("&#8226;"));
            dots_layout[i].setTextSize(35);

            dots.addView(dots_layout[i]);
        }

        if(dots_layout.length >0 )
        {
            dots_layout[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
            currentPos=position;

            if (position== 0)
            {
                letsGetStarted.setVisibility(View.INVISIBLE);
            }else if(position==1)
            {
                letsGetStarted.setVisibility(View.INVISIBLE);

            }else if(position==2)
            {
                letsGetStarted.setVisibility(View.INVISIBLE);
            }else{
                animation= AnimationUtils.loadAnimation(onBoarding.this,R.anim.bottom_animation);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
