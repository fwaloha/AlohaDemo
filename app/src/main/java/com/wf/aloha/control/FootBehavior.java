package com.wf.aloha.control;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;

import com.wf.aloha.utils.LogUtils;

/**
 * Created by wangfei on 2018/1/10.
 */

public class FootBehavior extends CoordinatorLayout.Behavior {

    private int ortationY;
    private final FastOutSlowInInterpolator mInterpolator;
    private boolean animating;

    public FootBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInterpolator = new FastOutSlowInInterpolator();
    }

    //    这两个使底部tab跟随appbarlayout同步显示隐藏
//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//        return dependency instanceof NestedScrollView;
//    }
//
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        int top = Math.abs(dependency.getTop());
//        child.setTranslationY(top);
//        return true;
//    }


//    这几个方法使底部隐藏显示


//        判断滑动方向
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    //    滑动处理
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (ortationY > 0 && dy < 0 || ortationY < 0 && dy > 0) {
            ortationY = 0;
            child.animate().cancel();
        }

        ortationY += dy;
        LogUtils.d("-----",ortationY+"::");
        int visibility = child.getVisibility();
        if (ortationY > child.getMeasuredHeight() && visibility == View.VISIBLE && !animating) {
            hide(child);
        } else if (ortationY < 0 && (visibility == View.GONE || visibility == View.INVISIBLE)&&!animating) {
            show(child);
        }
    }

    private void show(final View child) {
        ViewPropertyAnimator animator = child.animate().translationY(0).setInterpolator(mInterpolator).setDuration(2000);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                child.setVisibility(View.VISIBLE);
                LogUtils.d("-----height",-child.getHeight()+"");
                animating = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
//                hide(child);
                animating = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void hide(final View child) {
        ViewPropertyAnimator animator = child.animate().translationY(child.getHeight()).setInterpolator(mInterpolator).setDuration(2000);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animating = true;

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                child.setVisibility(View.INVISIBLE);
                LogUtils.d("-----height hide",child.getHeight()+"");
                animating = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
//                show(child);
                animating = false;

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
