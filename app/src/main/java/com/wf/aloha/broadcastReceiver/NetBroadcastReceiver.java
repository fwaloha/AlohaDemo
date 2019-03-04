package com.wf.aloha.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wf.aloha.utils.ToastUtils;

public class NetBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectMge = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectMge.getActiveNetworkInfo();
        if(networkInfo!=null){
            ToastUtils.showInstance("network is ok"+networkInfo.isAvailable());
        }else {
            ToastUtils.showInstance("error  !!!!");
        }
    }
}
