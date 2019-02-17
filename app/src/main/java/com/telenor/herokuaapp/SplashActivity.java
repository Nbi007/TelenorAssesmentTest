package com.telenor.herokuaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.telenor.herokuaapp.view.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    private Context context;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = SplashActivity.this;
        openHomeScreen();
    }

    private void openHomeScreen() {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                        startActivity(new Intent(context, HomeActivity.class));

                    finish();
                }
            }, 3000);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            if (handler != null)
                handler.removeCallbacksAndMessages(null);
        }

}
