package com.wf.aloha.ui;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wf.aloha.R;
import com.wf.aloha.utils.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CamActivity extends AppCompatActivity {

    @BindView(R.id.bt_camera)
    Button btCamera;
    @BindView(R.id.bt_image)
    Button btImage;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    private Uri imageUri;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_camera, R.id.bt_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_camera:
                openCamera();
                break;
            case R.id.bt_image:
                int i = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (i != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
                    } else {
                        openAlum();
                    }
                } else {
                    openAlum();

                }
                break;
        }
    }

    private void openAlum() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 104);
    }


    private void openCamera() {
        File file = new File(getCacheDir(), "cap.png");
        String path = getCacheDir().getPath();
        String path1 = getExternalCacheDir().getPath();
        LogUtils.d("-----path", path + "::" + path1);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(this, "com.wf.aloha.fileprovider", file);
        } else {
            imageUri = Uri.fromFile(file);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (i == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(intent, 100);
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
            }
        } else {
            startActivityForResult(intent, 100);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        ivShow.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case 104:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (DocumentsContract.isDocumentUri(this, uri)) {

                            String id = DocumentsContract.getDocumentId(uri);
                            if ("com.android.providers.media.documents".equalsIgnoreCase(uri.getAuthority())) {
                                String nid = id.split(":")[1];
                                String type = id.split(":")[0];
                                Uri contentUri = null;
                                if("image".equals(type)){
                                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                                }else if("video".equals(type)){
                                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                                }else if("audio".equals(type)){
                                    contentUri  = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                                }
                                String selection = "_id=" + nid;
                                imagePath = getImagePath(contentUri, selection);
                            } else if ("com.android.providers.download.documents".equalsIgnoreCase(uri.getAuthority())) {
                                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                                imagePath = getImagePath(contentUri, null);
                            }else if("com.android.externalstorage.documents".equalsIgnoreCase(uri.getAuthority())){
                                String type = id.split(":")[0];
                                if("primary".equalsIgnoreCase(type)){
                                    imagePath = Environment.getExternalStorageDirectory() + "/" + id.split(":")[1];
                                }
                            }
                        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                            imagePath = getImagePath(uri, null);
                        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                            imagePath = uri.getPath();
                        }
                    } else {
                        imagePath = getImagePath(uri, null);
                    }
                    showPic(imagePath);
                }
                break;
        }
    }

    private String getImagePath(Uri uri, String selection) {
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        String path = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
        }
        return path;
    }

    private void showPic(String imagePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ivShow.setImageBitmap(bitmap);
    }
}
