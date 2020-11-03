package com.dev.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.ErrorManager;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private float height, weight;
    private TextView txtHeight, age;
    private int count_weight = 50, count_age = 19;
    private RelativeLayout weight_plus, weight_minus, age_plus, age_minus;
    private Button calculate;

    public void male(View view) {
        ImageView image;
        image = (ImageView) findViewById(R.id.male_clicked);
        image.setImageResource(R.drawable.ic_male_clicked);

        ImageView image2;
        image2 = (ImageView) findViewById(R.id.female_clicked);
        image2.setImageResource(R.drawable.ic_woman);

    }

    public void female(View view) {
        ImageView image;
        image = (ImageView) findViewById(R.id.female_clicked);
        image.setImageResource(R.drawable.ic_woman_clicked);

        ImageView image2;
        image2 = (ImageView) findViewById(R.id.male_clicked);
        image2.setImageResource(R.drawable.ic_male);
    }

    public void about(View view) {
        Intent launchIntent = new Intent();
        launchIntent.setClass(MainActivity.this, AboutActivity.class);
        startActivity(launchIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = MainActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.app_col_10));

        age_minus = findViewById(R.id.age_minus);
        age_plus = findViewById(R.id.age_plus);

        weight_minus = findViewById(R.id.weight_minus);
        weight_plus = findViewById(R.id.weight_plus);
        calculate = findViewById(R.id.calculate);

        checkAge();
        checkWeight();
        seekBarStatus();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });


    }

    private void seekBarStatus() {
        SeekBar Seekbar = findViewById(R.id.seekBar);
        txtHeight = findViewById(R.id.height_txt);

        Seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String ht = progress + getResources().getString(R.string.centimeter);
                txtHeight.setText(ht);
                height = (float) progress / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkWeight() {
        final TextView weight_txt = findViewById(R.id.weight);

        weight_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_weight++;
                weight_txt.setText(String.valueOf(count_weight));
            }
        });

        weight_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_weight <=2)
                {
                    Toasty.error(getApplicationContext(), R.string.underweight, Toast.LENGTH_SHORT, true).show();
                }
                else {
                    count_weight--;
                    weight_txt.setText(String.valueOf(count_weight));
                }

            }
        });

        weight = Float.parseFloat(weight_txt.getText().toString());
    }

    private void checkAge() {
        age = findViewById(R.id.age);

        age_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_age++;
                age.setText(String.valueOf(count_age));
            }
        });

        age_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_age <=2)
                {
                    Toasty.error(getApplicationContext(), R.string.underage, Toast.LENGTH_SHORT, true).show();
                }
                 else
                count_age--;
                age.setText(String.valueOf(count_age));
            }
        });
    }


    private void calculateBmi() {
        checkWeight();
        seekBarStatus();
        float mBMI = weight / (height * height);
        Intent i = new Intent(MainActivity.this, SplashActivity.class);
        i.putExtra("BMI", mBMI);
        startActivity(i);

        //    Toast.makeText(getApplicationContext(), BMI +" Is ",Toast.LENGTH_SHORT).show();
        //   Log.i(String.valueOf(BMI), "= BMI VALUE" );

    }

}