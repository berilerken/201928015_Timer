package com.example.a201928105_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



//import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private Button topButton, bottomButton;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 30000; // 30 seconds for the timer
    private boolean topButtonIsActive = true;
    private boolean bottomButtonIsActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topButton = findViewById(R.id.top_button);
        bottomButton = findViewById(R.id.bottom_button);
        timerTextView = findViewById(R.id.timer_textview);

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topButtonIsActive) {
                    startTimer();
                    topButton.setText("Stop (Player 1)");
                    bottomButton.setEnabled(false);
                    topButtonIsActive = false;
                } else {
                    stopTimer();
                    topButton.setText("Start (Player 1)");
                    bottomButton.setEnabled(true);
                    topButtonIsActive = true;
                }
            }
        });

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomButtonIsActive) {
                    startTimer();
                    bottomButton.setText("Stop (Player 2)");
                    topButton.setEnabled(false);
                    bottomButtonIsActive = false;
                } else {
                    stopTimer();
                    bottomButton.setText("Start (Player 2)");
                    topButton.setEnabled(true);
                    bottomButtonIsActive = true;
                }
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                String winnerText;
                if (topButtonIsActive) {
                    winnerText = "Player 1 Wins!!!";
                } else {
                    winnerText = "Player 2 Wins!!!";
                }
                timerTextView.setText(winnerText);
                topButton.setEnabled(false);
                bottomButton.setEnabled(false);
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;
        if (seconds < 10) {
            timeLeftText = minutes + ":0" + seconds;
        } else {
            timeLeftText = minutes + ":" + seconds;
        }

        timerTextView.setText(timeLeftText);
    }
}
