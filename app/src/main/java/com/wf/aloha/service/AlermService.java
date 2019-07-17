package com.wf.aloha.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.v4.app.ActivityCompat;

import com.wf.aloha.ui.MainActivity;
import com.wf.aloha.ui.ToolbarActivity;
import com.wf.aloha.utils.LogUtils;

import java.util.ArrayList;

public class AlermService extends Service {
    public AlermService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        final long timenow = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("-----alert:"," time"+SystemClock.elapsedRealtime());
            }
        }).start();
        
        int minite = 6 * 1000;
        Intent intent1 = new Intent(getApplicationContext(), AlermService.class);
        PendingIntent service = PendingIntent.getService(getApplicationContext(), 0, intent1, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+minite,service);
        
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
