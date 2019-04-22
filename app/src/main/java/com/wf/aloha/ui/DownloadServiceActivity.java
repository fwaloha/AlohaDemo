package com.wf.aloha.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wf.aloha.R;
import com.wf.aloha.service.DownloadService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadServiceActivity extends AppCompatActivity {

    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_pause)
    Button btPause;
    @BindView(R.id.bt_cancel)
    Button btCancel;

    private DownloadService.DownloadBinder mBinder;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_service);
        ButterKnife.bind(this);

        mServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinder = (DownloadService.DownloadBinder) service;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        DownloadService downloadService = new DownloadService();
        Intent intent = new Intent(this, downloadService.getClass());
        startService(intent);

        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @OnClick({R.id.bt_start, R.id.bt_pause, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                mBinder.startDownload("");
                break;
            case R.id.bt_pause:
                mBinder.pauseDownload();
                break;
            case R.id.bt_cancel:
                mBinder.cancelDownload();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
