package com.wf.aloha.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.wf.aloha.R;
import com.wf.aloha.ui.MainActivity;
import com.wf.aloha.utils.ToastUtils;

/**
 * service 运行在主线程，所以，耗时的操作，在oncreate里必须新开一个子线程。然后完成后执行stopself功能。比较麻烦，可以考虑用intentservice代替。
 */
public class ServiceDemo extends Service {
    public ServiceDemo() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        bindService(intent,new MyConnection(),0);
        return mBinder;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.showInstance("service created");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
        Notification notify = new NotificationCompat.Builder(this, "channelId")
                .setContentTitle("service title")
                .setContentText("service content")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_animal_dui))
                .setSmallIcon(R.drawable.ic_menu_slideshow)
                .build();
        startForeground(1,notify);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                //todo 此处处理service里的任务。
//                stopSelf();执行完后自销毁。
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastUtils.showInstance("service destroyed");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        unbindService(new MyConnection());
        return super.onUnbind(intent);
    }


    private class MyConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    
    private TestBinder mBinder = new TestBinder();

    private class TestBinder extends Binder {
        
    }
}
