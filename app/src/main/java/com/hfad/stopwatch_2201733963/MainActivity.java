package com.hfad.stopwatch_2201733963;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wRunning = savedInstanceState.getBoolean("wRunning");
        }
        Timer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.getBoolean("wRunning", wRunning);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wRunning){
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wRunning = running;
        running = false;
    }

    public void onStart(View view){
        running = true;
    }
    public void onStop(View view){
        running = false;
    }
    public void onRestart(View view){
        running = false;
        seconds = 0;
    }

    private void Timer(){
        final TextView time = (TextView)findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int min = (seconds%3600)/60;
                int sec = seconds%60;
                String timer = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, min, sec);
                time.setText(timer);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }



}