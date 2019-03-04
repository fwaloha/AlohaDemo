package com.wf.aloha.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewStubActivity extends AppCompatActivity {

    @BindView(R.id.et_)
    EditText mEtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        ButterKnife.bind(this);
        ckPermission();

        Intent intent = getIntent();
        String smscode = intent.getStringExtra("msg");
        
        mEtCode.setText(smscode);
    }

    /**
     * 动态权限检查
     */
    private void ckPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = checkSelfPermission(Manifest.permission.READ_SMS);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                //判断是否勾选了不再提示对话框。
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)) {
                    ToastUtils.showInstance("需要读取短信权限，否则app无法运行");
                }
                requestPermissions(new String[]{Manifest.permission.READ_SMS}, 100);
                return;
            }
        }
    }

        /**
     * 动态权限检查
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ToastUtils.showInstance("需要您手动打开短信权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
