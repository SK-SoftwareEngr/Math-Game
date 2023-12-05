package com.saniya.mathgame;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GameFunctions {
    private CountDownTimer timer;
    private int life = 3;
    private static final long START_TIMER = 15000;
    private boolean timer_running;
    private long time_left = START_TIMER;

    public void ebGone(EditText et, Button btn){
        et.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
    }

    public void updateText(TextView tv){ //updating value of tvTime every second
        int seconds = (int) (time_left / 1000) % 60;
        String time_left_str = String.format(Locale.getDefault(), "%02d", seconds);
        tv.setText(time_left_str);
    }

    public void lifeZero(TextView tv1, Context context, int score){
        tv1.setText(R.string.wrong_answer);
        Toast.makeText(context, "Game Over", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(context, com.saniya.mathgame.Activity6.class);
        intent.putExtra("score", score);
        context.startActivity(intent);
    }

    public void startTimer(TextView tvTime, TextView tvQuestion, TextView tvLife, int score, Context context){
        timer = new CountDownTimer(time_left, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left = millisUntilFinished;
                updateText(tvTime);
            }

            @Override
            public void onFinish() {
                //When timer runs out
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText(tvTime);

                if (life >0){
                    life -= 1;
                    tvQuestion.setText(R.string.time_up);
                    tvLife.setText("" + life);
                }
                else{
                    lifeZero(tvQuestion, context, score);
                }

            }
        }.start();

        timer_running = true;
    }

    public void pauseTimer(){
        if (timer != null){
            timer.cancel();
            timer_running = false;
        }
    }

    public void resetTimer(){
        time_left = START_TIMER;
    }
}
