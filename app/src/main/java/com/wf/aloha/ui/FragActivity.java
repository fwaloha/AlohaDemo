package com.wf.aloha.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wf.aloha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        Button btChange = findViewById(R.id.bt_change);
        btChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_change:
                changeFrag();
                break;
        }
    }

    private void changeFrag() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(new RightFragment());
        transaction.replace(R.id.frag_right,new LeftFragment());
        transaction.addToBackStack(null);//添加返回键支持
        transaction.commit();
    }
}
