package com.example.flex_break.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.flex_break.R;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder extends Button {

    private int time;
    private Timer timer;
    private TimerTask timerTask;
    private Context context;
    private boolean started;

    public Reminder(Context context, int time) {
        super(context);
        this.time = time * 1000;
        started = false;
        this.context = context;
        this.setText("Press ME");


        timer = new Timer();
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!started) {
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            Log.d("Timer", "create notification for");
                            timerNotify();
                        }
                    };
                    setText("Timer has started");
                    timer.schedule(timerTask, getTime());
                    started = true;
                }
            }
        });
    }

    public void timerNotify() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context, "14");
        builder.setSmallIcon(R.drawable.ic_flex_stretch_notification_icon);
        builder.setContentTitle("Flex Stretch");
        builder.setContentText("It's time to stretch!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.context);
        notificationManager.notify(1, builder.build());
        started = false;
        setText("Press ME");
    }

    public int getTime() {
        return this.time;
    }
}
