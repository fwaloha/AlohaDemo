package com.wf.aloha;

import android.app.Activity;

import java.util.ArrayList;

public class ActivityList {
    private static ArrayList<Activity> mlist = new ArrayList<>();

    public static void addActivity(Activity baseTest) {
        mlist.add(baseTest);
    }

    public static void removeActivity(Activity baseTest) {
        mlist.remove(baseTest);
    }

    public static void clearAll() {
        for (Activity item : mlist) {
            if (!item.isFinishing()) {
                item.finish();
            }
        }
        mlist.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
