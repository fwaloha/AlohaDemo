package com.wf.aloha.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wf.aloha.BaseActivity;
import com.wf.aloha.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//tencet qq music
public class RadioActivity extends AppCompatActivity {

    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_music)
    TextView tvMusic;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_radio);
        ButterKnife.bind(this);
        
        initData();
        initListener();
    }

    public void initData() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        view.getLayoutParams().width = point.x/2;
        
    }

    public void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.tv_video, R.id.tv_music})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_video:
                break;
            case R.id.tv_music:
                break;
        }
    }
}
