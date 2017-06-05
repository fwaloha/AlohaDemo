package com.wf.aloha.utils;

import android.widget.Toast;

import com.wf.aloha.AppRoot;

/**
 * Created by wangfei on 2017/6/1.
 */

public class ToastUtils {

    /* 单例toast */
    private static Toast toast = null;
    public static Toast getInstance() {
        if (toast == null) {
            synchronized (ToastUtils.class) {
                toast = Toast.makeText(AppRoot.getContext(), "", Toast.LENGTH_SHORT);
            }
        }
        return toast;
    }
    public static void showInstance(String str) {
        getInstance();
        toast.setText(str);
        toast.show();
    }
}
