package com.wf.aloha.baseview;

import android.view.MotionEvent;
import android.view.View;

import com.wf.aloha.utils.LogUtils;

public class ViewInter implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float startX = event.getX();
                float startY = event.getY();
                LogUtils.d("-----view down", startX + ":" + startY + "--" + event.getRawX() + ":" + event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("-----view move", event.getX() + "--" + event.getRawX() + "---" + v.getTranslationX());

                
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
