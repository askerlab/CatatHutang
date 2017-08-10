package com.askerlab.catathutang;

/**
 * Created by root on 29/05/17.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        String customFont = "fonts/CaviarDreams.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), customFont);
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setTypeface(typeface);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setTypeface(typeface);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(0 , R.anim.fade_activity);
            }
        }, splashInterval);

    };
}
