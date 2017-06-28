package com.wf.aloha;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wf.aloha.utils.LogUtils;

/**
 */
public class TempView extends View {
    private int lastX;
    private int lastY;
    private int mWidth;
    private int mHeight;

    private int screenWidth;
    private int screenHeight;

    public TempView(Context context) {
        super(context);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        screenWidth = display.widthPixels;
        screenHeight = display.heightPixels;
    }

    public TempView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        LogUtils.d("------","zou zhe le");
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                ViewGroup.LayoutParams layoutParams1 = getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;
                LogUtils.d("-----","layoutparams left"+ layoutParams.leftMargin+"--layoutparams top" + layoutParams.topMargin+"--x:"+x+"--y:"+y+"last x:"+lastX+"last y:"+lastY);


                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                
                setLayoutParams(layoutParams1);
                requestLayout();
                break;
            }

            case MotionEvent.ACTION_UP: {

                break;
            }

        }
        lastX = x;
        lastY = y;
        return true;
    }
}
