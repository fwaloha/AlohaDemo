package com.wf.aloha.ui;

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.wf.aloha.R;
import com.wf.aloha.baseclass.BaseTitlebar;
import com.wf.aloha.utils.ToastUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Test2Activity extends AppCompatActivity {

    @BindView(R.id.fl)
    FlexboxLayout flexlayout;
    @BindView(R.id.bt_share)
    Button btShare;
    @BindView(R.id.bt_notify)
    Button btNotify;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.bt_otheract)
    Button btOtheract;
    @BindView(R.id.bt_sh)
    Button btSh;
    @BindView(R.id.bt_other)
    Button btOther;
    @BindView(R.id.bt_broadtcast)
    Button btBroadtcast;
    @BindView(R.id.bt_file)
    Button btFile;
    private NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String value = savedInstanceState.getString("key");
        }
//        Intent intent = new Intent("");
//        intent.putExtras(savedInstanceState);
//        Bundle extras = intent.getExtras();
//        int taskId = getTaskId();
//        getClass().getSimpleName();

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);

        for (int i = 0; i < 6; i++) {
            TextView textView = new TextView(this);
            textView.setText("this is a" + i * i * i * 10);
            textView.setPadding(16, 8, 16, 8);
            textView.setBackgroundColor(getResources().getColor(R.color.bg_cyan));
            flexlayout.addView(textView);
        }

        BaseTitlebar baseBar = findViewById(R.id.bar);
        baseBar.setTitle("");
    }

    @OnClick({R.id.bt_share, R.id.bt_notify, R.id.bt_cancel, R.id.bt_otheract, R.id.bt_sh, R.id.bt_other, R.id.bt_broadtcast
    ,R.id.bt_file})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "https://www.baidu.com");
                intent.setType("text/plain");
//        intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI"));//只能分享图片。
//        intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
                startActivity(Intent.createChooser(intent, "Share text..."));
                break;
            case R.id.bt_notify:
                notiMang();

                break;
            case R.id.bt_cancel:
                mNotifyManager.cancel(0);
                break;
            case R.id.bt_otheract:
                openDkd();//开启第三方
                break;
            case R.id.bt_sh:
                SharedPreferences shareTest = this.getSharedPreferences("shareTest", MODE_PRIVATE);
                SharedPreferences.Editor editor = shareTest.edit();
                String str = "媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道媒体报道";
                editor.putString("testKey", str);
                ToastUtils.showInstance("sharedpreference" + str.length());
                editor.commit();
                break;
            case R.id.bt_other:
                Intent hideactivity = new Intent(this, FragActivity.class);
//                hideactivity.addCategory("com.dd.MY_CAT");
//                Intent intent1 = new Intent(Intent.ACTION_DIAL);
//                intent1.setData(Uri.parse("tel:10086"));
//                intent1.setData(Uri.parse("https://www.baidu.com"));
                startActivity(hideactivity);
                break;
            case R.id.bt_broadtcast:
                Intent intent1 = new Intent(this, BroadcastActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_file:
                Intent intent2 = new Intent(this, FileSaveActivity.class);
                startActivity(intent2);
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "value");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                ToastUtils.showInstance("add");
                break;
            case R.id.remove:
                ToastUtils.showInstance("remove");
                break;
        }
        return true;
    }

    //open 第三方
    private void openDkd() {
//        PackageManager packageManager = getPackageManager();
////        String packageName = packageManager.getInstallerPackageName("com.pitaya.daokoudai");
//        Intent intent = packageManager.getLaunchIntentForPackage("com.pitaya.daokoudai");
////        ActivityInfo activityInfo = intent.resolveActivityInfo(getPackageManager(), PackageManager.MATCH_DEFAULT_ONLY);
////        if(activityInfo!=null){
////        if(getPackageManager().resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY)!=null){
//        if(intent!=null){
//            startActivity(intent);
//        }else {
//            ToastUtils.showInstance("meiyou ci app");
//        }
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getMethod("addAssetPath", String.class);
            method.invoke(assetManager, "");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String pkgName = "com.pitaya.daokoudai";
        String laungherName = "com.pitaya.daokoudai.view.activity.welcome.StartActivity";
        ComponentName componentName = new ComponentName(pkgName, laungherName);
        Intent intent1 = new Intent();
        intent1.setComponent(componentName);
        if (intent1.resolveActivityInfo(getPackageManager(), PackageManager.MATCH_DEFAULT_ONLY) != null) {
            startActivity(intent1);
        }
    }

    private void notiMang() {
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("channelId", "channelName", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{1000});
            int defaultSound = Notification.DEFAULT_SOUND;
//            notificationChannel.
//            notificationChannel.setSound(DEFAULT_SOUND,null);
            mNotifyManager.createNotificationChannel(notificationChannel);
            Notification notif = new Notification.Builder(this)
                    .setChannelId("channelId")
                    .setContentTitle("Test")
                    .setSmallIcon(getApplicationInfo().icon)
                    .setContentText("hahaha zhe shi sha")
                    .setOnlyAlertOnce(true)
                    .build();
            mNotifyManager.notify(0, notif);
        }
    }

}
