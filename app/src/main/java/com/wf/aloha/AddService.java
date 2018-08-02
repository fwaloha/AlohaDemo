package com.wf.aloha;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AddService extends Service {
    public AddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
