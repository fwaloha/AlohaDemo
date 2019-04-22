package com.wf.aloha.service;

import android.app.IntentService;
import android.content.Intent;

import com.wf.aloha.utils.LogUtils;

public class IntentServiceDemo extends IntentService {

    public IntentServiceDemo() {
        super("IntentServiceDemo");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO: 2019/3/4 子线程里了。 
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("-----","intentservice destroy");
    }
}
