package com.example.flex_break.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder extends TextView {

    private Timer timer;
    private TimerTask timerTask;

    public Reminder(Context context, int time) {
        super(context);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d("Timer", "create notification ");
            }
        };
        setHeight(150);
        setWidth(150);
        setBackgroundColor(Color.rgb(200,200, 200));
        setTextSize(15);
        setText("it works!");
        timer = new Timer();
        timer.schedule(timerTask, time * 1000);
    }
}
