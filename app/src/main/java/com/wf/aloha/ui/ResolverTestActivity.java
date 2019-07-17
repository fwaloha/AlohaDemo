package com.wf.aloha.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import java.security.Permission;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResolverTestActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView list;
    private ArrayAdapter<String> mAdapter;

    ArrayList<String> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolver);
        ButterKnife.bind(this);

        initList();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            read();
        } else {
            readContact();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void readContact() {
        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (i == PackageManager.PERMISSION_GRANTED) {
            read();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }
    }

    private void read() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(name + ":" + number);
        }
        mAdapter.notifyDataSetChanged();
        if (cursor != null) {
            cursor.close();
        }
    }

    private void initList() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        list.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    read();
                } else {
                    ToastUtils.showInstance("please open contact permission");
                }
        }
    }
}
