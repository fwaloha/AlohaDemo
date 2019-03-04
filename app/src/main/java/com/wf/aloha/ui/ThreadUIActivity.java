package com.wf.aloha.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wf.aloha.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadUIActivity extends AppCompatActivity {

    private static final int TEXT_MSG = 1;
    @BindView(R.id.bt_msg)
    Button btMsg;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_ui);
        ButterKnife.bind(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case TEXT_MSG:
                        tvMsg.setText(msg.getData().getString("name"));
                        break;
                }
            }
        };
    }

    @OnClick(R.id.bt_msg)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.bt_msg:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = TEXT_MSG;
                        Bundle bundle = new Bundle();
                        bundle.putString("name","hahaha");
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }).start();
                new MyAsync().execute();
                break;
                
        }
    }
    
    public class MyAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            publishProgress(111);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }
    }
}
