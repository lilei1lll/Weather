package com.list.asus.weather2.util;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.list.asus.weather2.ChooseActivity;
import com.list.asus.weather2.MainActivity;
import com.list.asus.weather2.R;


public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.choose_title_layout, this);


        TextView backText = (TextView) findViewById(R.id.back_text);




    }
}
