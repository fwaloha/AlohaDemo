package com.wf.aloha.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wf.aloha.R;
import com.wf.aloha.service.ServiceDemo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {

    @BindView(R.id.bt_start_servce)
    Button btStartServce;
    @BindView(R.id.bt_stop_service)
    Button btStopService;
    @BindView(R.id.bt_bind)
    Button btBind;
    @BindView(R.id.bt_unbind)
    Button btUnbind;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        intent = new Intent(this, ServiceDemo.class);

    }

    @OnClick({R.id.bt_start_servce, R.id.bt_stop_service, R.id.bt_bind, R.id.bt_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start_servce:
                startService(intent);
                break;
            case R.id.bt_stop_service:
                if(intent!=null){
                    stopService(intent);
                }
                break;
            case R.id.bt_bind:
                break;
            case R.id.bt_unbind:
                break;
        }
    }
}
