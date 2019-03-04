package com.wf.aloha.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wf.aloha.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifiActivity extends AppCompatActivity {

    @BindView(R.id.bt_notify)
    Button btNotify;
    @BindView(R.id.bt_test)
    Button btTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi);
        ButterKnife.bind(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick({R.id.bt_notify, R.id.bt_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_notify:
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel notificationChannel = new NotificationChannel("channelId1", "chaname", NotificationManager.IMPORTANCE_HIGH);
                PendingIntent pendingi = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
                manager.createNotificationChannel(notificationChannel);
                Notification build = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("notify title")
                        .setChannelId("channelId1")
                        .setContentText("notify text")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build\n" +
                                "            notifications, send and sync data, and use voice actions. Get the official\n" +
                                "            Android IDE and developer tools to build apps for Android."))
                        .setContentIntent(pendingi)
                        .setAutoCancel(true)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                        .build();
                manager.notify(1,build);

                break;
            case R.id.bt_test:
                NotificationManager notimanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                NotificationChannel channel = new NotificationChannel("101", "testnotice", NotificationManager.IMPORTANCE_HIGH);
                PendingIntent activity = PendingIntent.getActivity(this, 1, new Intent(this,Test2Activity.class), PendingIntent.FLAG_CANCEL_CURRENT);
//                notimanager.createNotificationChannel(channel);
//                channel.enableVibration(true);
//                震动没能好使。
                Notification dddd = new NotificationCompat.Builder(this)
                        .setContentTitle("khaah")
                        .setContentText("heihei")
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                        .setContentIntent(activity)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVibrate(new long[]{0,1000,1000,1000})
                        .setLights(Color.CYAN,1000,1000)
                        .build();
                notimanager.notify(2,dddd);
                break;
        }
    }
}
