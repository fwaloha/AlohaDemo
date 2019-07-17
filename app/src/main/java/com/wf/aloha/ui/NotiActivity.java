package com.wf.aloha.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wf.aloha.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotiActivity extends AppCompatActivity {

    @BindView(R.id.bt_notify)
    Button btNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.bt_notify)
    public void onViewClicked() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {

            AudioAttributes attibutes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            Uri defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationChannel notichannel = new NotificationChannel("id2", "channelname", NotificationManager.IMPORTANCE_HIGH);
            notichannel.setSound(defaultUri,attibutes);
            notichannel.enableVibration(false);
            notichannel.enableLights(false);
            manager.createNotificationChannel(notichannel);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("title")
                    .setContentText("content content")
                    .setChannelId("id2")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pi)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .build();
            manager.notify(2, notification);
        }else {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle("low title");
            builder.setContentText("low content text");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pi);
            builder.setAutoCancel(true);
            Notification build = builder.build();
            manager.notify(2,build);
        }

    }
}
