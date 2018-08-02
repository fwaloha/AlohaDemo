package com.wf.aloha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initeView();
        initData();
        initListener();
    }

    public abstract void initeView();//        setContentView(R.layout.activity_base);

    public abstract void initData();

    public abstract void initListener();
}
