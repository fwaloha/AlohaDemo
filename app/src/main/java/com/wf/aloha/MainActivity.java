package com.wf.aloha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_pdf)
    TextView mTvPdf;
    @BindView(R.id.tv_get)
    TextView tvGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_get, R.id.tv_pdf, R.id.tv_post,R.id.tv_rxjava, R.id.tv_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get:
                Intent intent1 = new Intent(this, RetrofitGetActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_pdf:
                Intent intent = new Intent(this, LoadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_post:
                Intent intent2 = new Intent(this, RetrofitPostActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_rxjava:
                Intent intent3 = new Intent(this, RxJavaActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_download:
                Intent intent4 = new Intent(this, DownLoadActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
