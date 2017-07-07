package com.wf.aloha;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.wf.aloha.utils.NougatTools;
import com.wf.aloha.utils.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener, RectOnCamera.IAutoFocus {

    private static final int REQUST_CODE_PERMISSIONS = 100;
    @BindView(R.id.bt_dialog)
    Button btDialog;
    @BindView(R.id.cameraSurfaceView)
    CameraSurfaceView mCameraSurfaceView;
    @BindView(R.id.rectOnCamera)
    RectOnCamera mRectOnCamera;
    @BindView(R.id.takePic)
    Button takePicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mRectOnCamera.setIAutoFocus(this);
        takePicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takePic:
                mCameraSurfaceView.takePicture();
                break;
            default:
                break;
        }
    }


    @Override
    public void autoFocus() {
//        mCameraSurfaceView.setAutoFocus();
    }

    @OnClick(R.id.bt_dialog)
    public void onViewClicked() {
        new AlertDialog.Builder(this)
                .setTitle("选择照片")
                .setItems(new String[]{"camera", "pics"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ckPermission();
                                break;
                            case 1:
                                selectAlbum();
                                break;
                        }
                    }
                }).create().show();
//        .setMessage("这是啥多大了附近的拉萨的金佛")
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtils.showInstance("取消了");
//                    }
//                })
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtils.showInstance("haole haole");
//                    }
//                })                .create().show();


    }

    /**
     * 动态权限检查
     */
    private void ckPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            int permission1 = checkSelfPermission(Manifest.permission.CAMERA);
            if (permission != PackageManager.PERMISSION_GRANTED || permission1 != PackageManager.PERMISSION_GRANTED) {
                //判断是否勾选了不再提示对话框。
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    ToastUtils.showInstance("需要camera权限，否则app无法运行");
                }
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUST_CODE_PERMISSIONS);
                return;
            }
        }
        selectCamera();
    }

    /**
     * 动态权限检查
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUST_CODE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ToastUtils.showInstance("需要您手动打开camera权限和存储权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void selectCamera() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ToastUtils.showInstance("sd card cant find");
        }

        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ImageSelector/CameraImage/";
        File file1 = new File(absolutePath, System.currentTimeMillis() + ".jpg");


        String capName = getExternalFilesDir(null) + File.separator + System.currentTimeMillis() + ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(capName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri uriForFile = FileProvider.getUriForFile(CameraActivity.this, "com.wf.aloha.fileprovider", file);
            Uri uriForFile = NougatTools.formatFileProviderUri(CameraActivity.this, file);
//
//            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        startActivity(intent);

    }

    private void selectAlbum() {

    }
}
