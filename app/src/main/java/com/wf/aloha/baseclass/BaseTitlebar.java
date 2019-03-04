package com.wf.aloha.baseclass;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wf.aloha.R;

public class BaseTitlebar extends LinearLayout {

    private TextView tvTitle;

    public BaseTitlebar(Context context) {
        this(context,null);
    }

    public BaseTitlebar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater from = LayoutInflater.from(context);
        View view = from.inflate(R.layout.title_bar_layout, this);
        view.findViewById(R.id.ib_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity context1 = (Activity) getContext();
                context1.onBackPressed();
            }
        });

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseTitlebar);
        tvTitle = view.findViewById(R.id.tv_title);
        if(typedArray!=null){
            String title = typedArray.getString(R.styleable.BaseTitlebar_title);
            tvTitle.setText(title);
        }
    }

    public void setTitle(String s) {
        tvTitle.setText(s);
    }
}
