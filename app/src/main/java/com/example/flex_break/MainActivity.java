package com.example.flex_break;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.riyagayasen.easyaccordion.AccordionView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "test Channel";
    private final int NOTIFICATION_ID = 001;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        createNotificationChannel();

        //setContentView(R.layout.fragment_home);
        //create and display one reminder
        Reminder test = new Reminder(this.getApplicationContext(), 7);
        //LinearLayout reminderLayout = (LinearLayout) findViewById(R.id.reminders);
        //reminderLayout.addView(new Button(this.getApplicationContext()));
        //reminderLayout.addView(test);

    }

    public void onNotify(View v) {

    }

    public void timerNotify() {
        //Log.d("log", "Button works");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_flex_stretch_notification_icon);
        builder.setContentTitle("Flex Stretch");
        builder.setContentText("It's time to stretch!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            CharSequence name = "Test Channel";
            String description = "Test channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
/*
    private void testTimer(int time) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timerNotify();
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, time * 1000);
    }
 */

    private class Reminder extends TextView {

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
            setBackgroundColor(Color.rgb(45,240, 10));
            setTextSize(15);
            setText("it works!");
            timer = new Timer();
            timer.schedule(timerTask, time * 1000);
        }
    }

}
