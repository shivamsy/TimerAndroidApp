package com.example.shivam.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    final int minutes=0, seconds=0;
    TextView textView;
    SeekBar seekBar;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    Button start, reset;
    ImageView imageView;
    int p =0;


    public void reset() {
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        start.setEnabled(true);
        reset.setEnabled(false);
        textView.setText("00:30");
        imageView.animate().rotationBy(180).setDuration(1000);
    }

    public void onReset(View view) {
        reset();
    }

    public void countdown(int progress) {

        countDownTimer = new CountDownTimer(progress*1000, 1000) {

            public void onTick(long millisecondsUntilDone) {
                Log.i("left", Long.toString(millisecondsUntilDone));
                millisecondsUntilDone = millisecondsUntilDone/1000;
                int minutes = (int) millisecondsUntilDone/60;
                int seconds = (int) millisecondsUntilDone%60;

                if (minutes<10 && seconds<10)
                    textView.setText("0"+Integer.toString(minutes)+":0"+Integer.toString(seconds));
                else if (seconds < 10 && minutes>9)
                    textView.setText(Integer.toString(minutes)+":0"+Integer.toString(seconds));
                else if (minutes <10 && seconds>9)
                    textView.setText("0"+Integer.toString(minutes)+":"+Integer.toString(seconds));
                else
                    textView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
            }

            public void onFinish() {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tolling);
                mediaPlayer.start();
                reset();
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.timer);
        start = findViewById(R.id.start);
        reset = findViewById(R.id.reset);
        imageView = findViewById(R.id.imageView4);


        reset.setEnabled(false);

        seekBar.setMax(3599);
        seekBar.setProgress(30);
        p = 30;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress/60;
                int seconds = progress%60;
                p = progress;

            if (minutes<10 && seconds<10)
                textView.setText("0"+Integer.toString(minutes)+":0"+Integer.toString(seconds));
            else if (seconds < 10 && minutes>9)
                textView.setText(Integer.toString(minutes)+":0"+Integer.toString(seconds));
            else if (minutes <10 && seconds>9)
                textView.setText("0"+Integer.toString(minutes)+":"+Integer.toString(seconds));
            else
                textView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekBar.setEnabled(false);
                start.setEnabled(false);
                reset.setEnabled(true);
                countdown(p);
            }
        });

//        new CountDownTimer(10000, 1000) {
//
//            public void onTick(long millisecondsUntilDone) {
//                Log.i("tick tick ", Long.toString(millisecondsUntilDone/1000));
//            }
//
//            public void onFinish() {
//                Log.i("finished", "zzzzzzzzzzzz");
//            }
//        }.start();

//        final Handler handler = new Handler();
//
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("hello", "second passed by...");
//
//                handler.postDelayed(this, 1000);
//            }
//        };
//        handler.post(runnable);
    }
}
