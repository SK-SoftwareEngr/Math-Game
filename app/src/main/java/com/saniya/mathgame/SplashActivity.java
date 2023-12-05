package com.saniya.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private ImageView ivSplash;
    Animation animImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivSplash = findViewById(R.id.ivSplash);
        animImage = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_animation);

        ivSplash.setAnimation(animImage);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}