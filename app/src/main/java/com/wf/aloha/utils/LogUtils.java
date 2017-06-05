package com.wf.aloha.utils;

import android.util.Log;

import com.wf.aloha.AppRoot;

/**
 * Created by wangfei on 2017/6/1.
 */

public class LogUtils {

    private static final boolean ISDEBUG = AppRoot.isDebug();

    public static void d(String tag, String content) {
        if(ISDEBUG){
            Log.d(tag,content);
        }
    }
}
