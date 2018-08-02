package com.wf.aloha.ui;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wf.aloha.R;
import com.wf.aloha.broadcastReceiver.TimeReceiver;
import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.ToastUtils;
import com.zy.mocknet.MockNet;
import com.zy.mocknet.application.MockConnection;
import com.zy.mocknet.application.MockConnectionFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempActivity extends AppCompatActivity {

    @BindView(R.id.wv)
    WebView wv;
    @BindView(R.id.lv)
    ListView lv;
    private MockNet mMocknet;
    private TimeReceiver timeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
//        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showInstance("button clicked!");
//            }
//        });

        timeReceiver = new TimeReceiver();
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        mMocknet = MockNet.create()
                .addConnection(MockConnectionFactory.getInstance()
                        .createGeneralConnection("/*", "Hello, world!"))
                .addConnection(MockConnectionFactory.getInstance()
                        .createGeneralConnection(MockConnection.POST, "/api", "{\"status\": \"success post\"}"))
                .addConnection(MockConnectionFactory.getInstance()
                        .createGeneralConnection(MockConnection.GET, "/api", "{\"status\": \"success\"}"));


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.setPriority(Integer.MAX_VALUE);
        this.registerReceiver(timeReceiver, intentFilter);
        ToastUtils.showInstance("registed time");


        ArrayList<String> runningServiceList = getRunningService();
        MyAdapter myAdapter = new MyAdapter(runningServiceList);
        lv.setAdapter(myAdapter);

    }


    //获取运行中的服务service
    private ArrayList<String> getRunningService() {
        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = actManager.getRunningServices(Integer.MAX_VALUE);
        ArrayList<String> arrayList = new ArrayList<>();
        for (ActivityManager.RunningServiceInfo info :
                serviceList) {
            long activeSince = info.activeSince;
            String pkgName = info.process;
            ComponentName service = info.service;
            String packageName = service.getPackageName();
            String shortClassName = service.getShortClassName();

            LogUtils.d("-----service",packageName + "::"+ shortClassName);
            if(packageName.contains("pitaya")){
                arrayList.add(packageName + "::"+ shortClassName);
                
            }
        }
        return arrayList;
    }


    private void doGetServerTime() {
        try {
            URL url = new URL("http://www.daokoudai.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            long date = urlConnection.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final String format = simpleDateFormat.format(date);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showInstance(format);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMocknet.start(8001);

        wv.loadUrl("localhost:8001/api");
    }


    @Override
    protected void onStop() {
        super.onStop();
//        mMocknet.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(timeReceiver);
        ToastUtils.showInstance("unregisted time");
    }

    private class MyAdapter extends BaseAdapter {

        ArrayList<String> mList;

        public MyAdapter(ArrayList list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tv;
            if (convertView != null) {
                tv = (TextView) convertView;
            } else {
                tv = new TextView(parent.getContext());
            }
            tv.setText(mList.get(position));
            return tv;
        }
    }
}
