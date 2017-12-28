package com.wf.aloha;

import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasDrawActivity extends AppCompatActivity {

    @BindView(R.id.iv_dui)
    ImageView mIvDui;
    @BindView(R.id.bt)
    Button mBt;
    @BindView(R.id.view_top)
    View viewTop;
    private TypedArray typedArray;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_draw);
        ButterKnife.bind(this);

        initDraw();
        typedArray = getResources().obtainTypedArray(R.array.color_array);


    }

    private void initDraw() {
        mIvDui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animatable animatable = (Animatable) mIvDui.getDrawable();
                animatable.start();
            }
        });
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        int color = typedArray.getColor(counter % typedArray.length(), 0);
        viewTop.setBackgroundColor(color);
        counter++;
    }
}
