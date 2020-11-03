package com.dev.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView txtSample,rating,txtCategory;
    ImageView setImage;
    Button recheck;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Window window = ResultActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(ResultActivity.this, R.color.app_col_10));


        txtSample = findViewById(R.id.sample);
        rating = findViewById(R.id.rating);
        txtCategory = findViewById(R.id.category);
        recheck = findViewById(R.id.recheck);

        process();

        recheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent();
                launchIntent.setClass(ResultActivity.this, MainActivity.class);
                startActivity(launchIntent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void process()
    {
        float bmi = getIntent().getFloatExtra("BMI",0.0f);
        String str = String.format("%.02f", bmi);

        String category;

        if (bmi >18.5 && bmi <= 25) {
            category = "Normal (Healthy Weight)";
            txtCategory.setTextColor(Color.parseColor("#6cc73e"));
            rating.setTextColor(Color.parseColor("#6cc73e"));
            normal();
        } else if (bmi >25 && bmi <= 30) {
            category = "Overweight";
            txtCategory.setTextColor(Color.parseColor("#fdc72d"));
            rating.setTextColor(Color.parseColor("#fdc72d"));
            overweight();
        } else if (bmi >30 && bmi <= 35) {
            category = "Obese";
            txtCategory.setTextColor(Color.parseColor("#f46414"));
            rating.setTextColor(Color.parseColor("#f46414"));
            obeso();
        } else if (bmi >35 && bmi <= 40) {
            category = "Morbid Obese";
            txtCategory.setTextColor(Color.parseColor("#f41414"));
            rating.setTextColor(Color.parseColor("#f41414"));
            obeso();
        } else {
            category ="Morbid Obese";
            txtCategory.setTextColor(Color.parseColor("#f41414"));
            rating.setTextColor(Color.parseColor("#f41414"));
            morbidObesity();
        }

        txtSample.setText("Your BMI Rating Is ");
        rating.setText(str);
        txtCategory.setText("Your Category Is "+ category);
    }

    public void normal()
    {
        setImage = (ImageView) findViewById(R.id.setImage);
        setImage.setImageResource(R.drawable.normal);
    }
    public void overweight()
    {
        setImage = (ImageView) findViewById(R.id.setImage);
        setImage.setImageResource(R.drawable.overweight);
    }
    public void obeso()
    {
        setImage = (ImageView) findViewById(R.id.setImage);
        setImage.setImageResource(R.drawable.obeso);
    }
    public void morbidObesity()
    {
        setImage = (ImageView) findViewById(R.id.setImage);
        setImage.setImageResource(R.drawable.mobesity);
    }



}