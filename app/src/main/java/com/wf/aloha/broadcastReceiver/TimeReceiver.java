package com.wf.aloha.broadcastReceiver;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.wf.aloha.AppRoot;
import com.wf.aloha.service.MyintentService;
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

public class TimeReceiver extends BroadcastReceiver {

    private String format;

    @Override
    public void onReceive(final Context context, Intent intent) {
        ToastUtils.showInstance("time changed!");

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                doGetServerTime(context);
//            }
//        }.start();

        //2方式，intentservice
//        Intent intent1 = new Intent(context, MyintentService.class);
//        context.startService(intent1);
        //3方式 asynctask
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
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
    
    class MyAsyncTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            doGetServerTime();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ToastUtils.showInstance("start");
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            ToastUtils.showInstance("finish"+format);
        }
    }
}
