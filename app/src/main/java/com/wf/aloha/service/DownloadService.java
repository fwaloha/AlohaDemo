package com.wf.aloha.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.wf.aloha.R;
import com.wf.aloha.control.DownloadTask;
import com.wf.aloha.listener.DownloadListener;
import com.wf.aloha.ui.DownloadServiceActivity;
import com.wf.aloha.ui.MainActivity;

public class DownloadService extends Service {
    public static IBinder DownloadBinder = new Binder() {

    };
    private DownloadTask downloadTas;

    public DownloadService() {
    }

    DownloadListener listener = new DownloadListener() {

        @Override
        public void onProgress(int progress) {
            getNotificationMge().notify(1, getNotification("downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTas = null;
            stopForeground(true);
            getNotificationMge().notify(1, getNotification("success...", -1));
        }

        @Override
        public void onFailed() {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onCanceled() {

        }
    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DownloadBinder extends Binder {
        public void startDownload(String url) {

        }

        public void pauseDownload() {
            if (downloadTas != null) {
                downloadTas.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTas != null) {
                downloadTas.cancelDownload();
            } else {
            }
        }
    }

    private Notification getNotification(String title, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(this, 0, intent1, 0);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(intent)
                .setContentTitle(title);
        if (progress >= 0) {
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    private NotificationManager getNotificationMge() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


}
