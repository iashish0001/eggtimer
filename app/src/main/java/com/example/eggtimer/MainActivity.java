package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    boolean counterActive = false;
    Button button3;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button3.setText("GO!");
        counterActive = false;

    }

    public void goButton(View view){

        if(counterActive){
            resetTimer();


        }else {
            counterActive = true;
            seekBar.setEnabled(false);
            button3.setText("STOP!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();

                }
            }.start();
        }





    }
    public void updateTimer(int secondsLeft){

        int minutes = secondsLeft /60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);
        if (seconds <= 9){
            secondString = "0" + secondString;
        }
        textView.setText(Integer.toString(minutes) + ":" + secondString);



    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button3 = findViewById(R.id.button3);


        seekBar.setMax(600);
        textView.setText("0:30");
        seekBar.setProgress(30);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }
}
