package com.example.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutinflater;

    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    int images[]={
            R.drawable.image_m,
            R.drawable.doctor_image,
            R.drawable.mask_covid,
            R.drawable.share_covid
    };
    int headings[]={
            R.string.first_slide_title_top,
            R.string.second_slide_title_top,
            R.string.third_slide_title_top,
            R.string.fourth_slide_title_top
    };
    int descriptions []={
            R.string.first_slide_title_down,
            R.string.second_slide_title_down,
            R.string.third_slide_title_down,
            R.string.fourth_slide_title_down
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutinflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutinflater.inflate(R.layout.slides_layout,container,false);


        ImageView imageview=view.findViewById(R.id.imageView2);
        TextView heading=view.findViewById(R.id.textiew2);
        TextView desc=view.findViewById(R.id.textview);

        imageview.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);



        return view;
    }
}
