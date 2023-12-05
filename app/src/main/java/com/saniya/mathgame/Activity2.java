package com.saniya.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Activity2 extends AppCompatActivity {
    private TextView tvScore, tvLife, tvTime, tvQuestion;
    private EditText etAnswer;
    private Button btnSubmit, btnNext;
    int num1, num2, answer, score = 0, life = 3;
    Random random = new Random();
    private boolean isNumber;
    GameFunctions gameFunc = new GameFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Initialize elements
        tvScore = findViewById(R.id.tvScore);
        tvLife = findViewById(R.id.tvLife);
        tvTime = findViewById(R.id.tvTime);
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);

        //Game starts
        btnNext.setVisibility(View.GONE); //Next question button will appear only after user answers the question
        GameContinue();
        gameFunc.startTimer(tvTime, tvQuestion, tvLife, score, Activity2.this);

        btnSubmit.setVisibility(View.VISIBLE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uI = etAnswer.getText().toString().trim();
                int userInput = Integer.parseInt(uI);

                gameFunc.pauseTimer();

                if (uI.isEmpty())
                    {Toast.makeText(Activity2.this, "Please enter a valid answer", Toast.LENGTH_LONG).show();}
                else{
                    if(userInput == answer){
                        tvQuestion.setText(R.string.right_answer);
                        etAnswer.setText("");
                        score += 10;
                        tvScore.setText("" + score);

                        gameFunc.ebGone(etAnswer, btnSubmit);
                        btnNext.setVisibility(View.VISIBLE);
                    }
                    else{

                        life -=1;
                        tvLife.setText("" + life);
                        etAnswer.setText("");
                        gameFunc.ebGone(etAnswer, btnSubmit);

                        //When no life is left
                        if(life == 0){
                            gameFunc.lifeZero(tvQuestion, Activity2.this, score);
                            finish();
                        }
                        else{
                            GameContinue();
                            gameFunc.ebGone(etAnswer, btnSubmit);
                            tvQuestion.setText(R.string.wrong_answer);
                        }
                    }
                    btnNext.setVisibility(View.VISIBLE);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnNext.setVisibility(View.GONE);
                GameContinue();
                etAnswer.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                gameFunc.resetTimer();
                gameFunc.startTimer(tvTime, tvQuestion, tvLife, score, Activity2.this);
                gameFunc.updateText(tvTime);
            }
        });
    }

    private void GameContinue() {
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        answer = num1 + num2;

        tvQuestion.setText(num1 + " + " + num2);
    }


}