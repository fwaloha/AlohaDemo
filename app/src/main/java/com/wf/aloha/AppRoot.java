package com.wf.aloha;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.liulishuo.filedownloader.FileDownloader;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.xiaomi.MiPushRegistar;
//import com.umeng.message.IUmengRegisterCallback;
//import com.umeng.message.PushAgent;

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

        initCrash();
        //为了使用第三方封装的retrofit下载控件。
        FileDownloader.init(getApplicationContext());

//        PushAgent mPushAgent = PushAgent.getInstance(this);
////注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                Log.d("-----","device token:"+deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
        
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
////                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//                
//            }
//        };
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        initPush();
        registerActivityLifecycleCallbacks();
    }

    private void initPush() {
        UMConfigure.init(this,"5940ad6aaed17966cb000895","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"aa5ae782ba2e5be3c12edbaa23e7dfbd");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i("device_token","" + s);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

        MiPushRegistar.register(this, "2882303761517614217", "5151761430217");
        HuaWeiRegister.register(new AppRoot());
    }

    private void initCrash() {
        CrashReport.initCrashReport(getApplicationContext(),"aa5f1a25a1",true);
    }

    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                PushAgent.getInstance(activity).onAppStart();
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public static boolean isDebug() {
        return mDebugMode;
    }
    
    public static void setDebug(boolean isDebug){
        mDebugMode = isDebug;
    }

}
