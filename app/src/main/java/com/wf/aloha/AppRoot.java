package com.wf.aloha;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by wangfei on 2017/6/1.
 */

public class AppRoot extends Application {

    private static Context mContext;
    private static boolean mDebugMode = true;

    public static Context getContext() {
        return  mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //为了使用第三方封装的retrofit下载控件。
        FileDownloader.init(getApplicationContext());
    }

    public static boolean isDebug() {
        return mDebugMode;
    }
    
    public static void setDebug(boolean isDebug){
        mDebugMode = isDebug;
    }
}
