package com.wf.aloha.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wf.aloha.utils.ToastUtils;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
        ToastUtils.showInstance("开机了");
    }
}
