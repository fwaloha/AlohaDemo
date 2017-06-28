package com.wf.aloha;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by wangfei on 2017/6/15.
 */

public class AttrTest extends LinearLayout {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_text)
    TextView tvText;

    public AttrTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.test_attr_layout, this, true);

        tvText.setText("hahaha this is attr tools");

//        //处理自定义属性。TODO 未成功 使用会崩溃
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.AttrTest);
        int indexCount = attrArray.getIndexCount();
        for(int i=0;i<indexCount;i++){
            int attrName = attrArray.getIndex(i);
            switch (attrName){
                case R.styleable.AttrTest_tab_icon:
                    iv.setImageResource(attrArray.getResourceId(R.styleable.AttrTest_tab_icon,R.mipmap.ic_launcher));
                    break;
                case R.styleable.AttrTest_tab_name:
                    tvText.setText(attrArray.getString(R.styleable.AttrTest_tab_name));
                    break;
            }
        }
    }
}
