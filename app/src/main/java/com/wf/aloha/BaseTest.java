package com.wf.aloha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityList.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActivityList.removeActivity(this);
    }
}
