package com.saniya.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity6 extends AppCompatActivity {
    TextView tvEndScore;
    Button btnPlayAgain, btnExit;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        tvEndScore = findViewById(R.id.tvEndScore);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnExit = findViewById(R.id.btnExit);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        tvEndScore.setText("Your Score: " + score);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity6.this, com.saniya.mathgame.MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}