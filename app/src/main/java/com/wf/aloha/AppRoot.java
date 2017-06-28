package com.wf.aloha;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.wf.aloha.utils.LogUtils;

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

        PushAgent mPushAgent = PushAgent.getInstance(this);
        LogUtils.d("-----","here");
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtils.d("-----","deviceToken:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.d("-----","fail msg:"+s1+"::"+s);
            }
        });

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

    }

    public static boolean isDebug() {
        return mDebugMode;
    }
    
    public static void setDebug(boolean isDebug){
        mDebugMode = isDebug;
    }
}
