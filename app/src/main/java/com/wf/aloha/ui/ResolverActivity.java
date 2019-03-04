package com.wf.aloha.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import java.security.Permission;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResolverActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView list;

    ArrayList<String> contactList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolver);
        ButterKnife.bind(this);
        
        initList();
        updateList();
        
    }

    private void updateList() {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            checkPer();
        }else {
            readContactList();
        }
    }

    private void checkPer() {
        int per = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if(per == PackageManager.PERMISSION_GRANTED){
            readContactList();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},100);
        }
    }

    //读取通讯录 Contentresolver
    private void readContactList() {
        Cursor queryCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (queryCursor.moveToNext()){
            String name = queryCursor.getString(queryCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneStr = queryCursor.getString(queryCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(name+"\n"+phoneStr);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initList() {
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactList);
        list.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContactList();
                }else {
                    ToastUtils.showInstance("请打开读取通讯录权限");
                }
                break;
        }
    }
}
