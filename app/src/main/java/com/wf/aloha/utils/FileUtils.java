package com.wf.aloha.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by wangfei on 2017/6/5.
 */

public class FileUtils {
    //获取工程存储目录的根目录地址，如 /storage/emulated/0/Android/data/com.wf.aloha/files/
    public static String getAppRootPath(Context context) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ToastUtils.showInstance("存储异常，请检查！");
            return "";
        }
        File rootPath = context.getExternalFilesDir(null);
        return rootPath.toString() + File.separator;
    }
}
