package com.wf.aloha.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.ToastUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

/**
 * Created by wangfei on 2018/3/13.
 */

public class MyintentService extends IntentService {

    private String format;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyintentService(String name) {
        super(name);
    }
    public MyintentService(){
        super("");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        doGetServerTime();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastUtils.showInstance("time is :"+format);
    }

    private void doGetServerTime() {
        try {
            URL url = new URL("http://www.daokoudai.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            long date = urlConnection.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = simpleDateFormat.format(date);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    ToastUtils.showInstance(format);
//                }
//            });
            LogUtils.d("-----time", format);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
