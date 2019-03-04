package com.wf.aloha.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wf.aloha.R;
import com.wf.aloha.broadcastReceiver.MyReceiver;
import com.wf.aloha.broadcastReceiver.NetBroadcastReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BroadcastActivity extends AppCompatActivity {

    @BindView(R.id.bt_send)
    Button btSend;
    @BindView(R.id.bt_local)
    Button btLocal;
    private NetBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);

//        registBroad();
        registLocalBroad();
    }

    private void registLocalBroad() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(new MyReceiver(),new IntentFilter("com.wf.aloha.mybroadcast"));
    }

    private void registBroad() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        mReceiver = new NetBroadcastReceiver();
        this.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    @OnClick({R.id.bt_send,R.id.bt_local})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_send:
                cendBroad();
                break;
            case R.id.bt_local:
                cendLocalBroad();
                break;
        }
    }

    //发送 本地广播 保密使用。
    private void cendLocalBroad() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("com.wf.aloha.mybroadcast");
        manager.sendBroadcast(intent);
    }

    //发送全局广播
    private void cendBroad() {
        Intent intent = new Intent("com.wf.aloha.mybroadcast");
        sendBroadcast(intent);
//        sendOrderedBroadcast(intent,null);
//        ToastUtils.showInstance("sended");
    }

}
