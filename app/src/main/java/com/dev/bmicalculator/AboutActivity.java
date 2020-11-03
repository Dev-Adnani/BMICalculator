package com.dev.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    public void Gmail(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);

            String[] strTo = {getString(R.string.mail_id)};

            intent.putExtra(Intent.EXTRA_EMAIL, strTo);
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name));

            intent.setType("message/rfc822");

            intent.setPackage("com.google.android.gm");

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(AboutActivity.this, "Sorry you don't have any Gmail installed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void Twitter(View view) {
        Uri uri = Uri.parse("http://twitter.com/AdnaniDev");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.twitter.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://twitter.com/AdnaniDev")));
        }
    }

    public void LinkedIn(View view) {
        Uri uri = Uri.parse("http://in.linkedin.com/in/dev-adnani");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.linkedin.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://in.linkedin.com/in/dev-adnani")));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Window window = AboutActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(AboutActivity.this, R.color.app_col_10));
    }
}