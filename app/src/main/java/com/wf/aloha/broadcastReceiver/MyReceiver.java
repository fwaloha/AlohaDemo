package com.wf.aloha.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wf.aloha.utils.ToastUtils;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        ToastUtils.showInstance("这是自定义广播！");
//        abortBroadcast();
    }
}
