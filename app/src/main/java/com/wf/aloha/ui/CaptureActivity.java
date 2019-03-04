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
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaptureActivity extends AppCompatActivity {

    private static final int OPEN_ALBUM = 400;
    @BindView(R.id.bt_capture)
    Button btCapture;
    @BindView(R.id.tv_pic_name)
    TextView tvPicName;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.bt_album)
    Button btAlbum;
    private Uri fileUri;
    private File picFile;
    private Intent captureIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_capture, R.id.bt_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_album:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int i1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (i1 == PackageManager.PERMISSION_GRANTED) {
                        openAlbum();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 300);
                    }
                } else {
                    openAlbum();
                }
                break;
            case R.id.bt_capture:
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                picFile = new File(getExternalCacheDir(), format.format(new Date(System.currentTimeMillis())) + ".jpg");
                if (picFile.exists()) {
                    picFile.delete();
                    try {
                        picFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fileUri = FileProvider.getUriForFile(this, "com.wf.aloha.fileprovider", picFile);
                } else {
                    fileUri = Uri.fromFile(picFile);
                }

                captureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int i = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                    if (i == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(captureIntent, 100);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 200);
                    }
                } else {
                    startActivityForResult(captureIntent, 100);
                }
                break;
            default:
                break;
        }

    }

    //打开相册
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode != RESULT_OK) {
                    return;
                }
                try {
                    ivPic.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri)));
                    tvPicName.setText(picFile.getName() + "::" + picFile.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case OPEN_ALBUM:
                if (resultCode != RESULT_OK) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    handleAlbumOnKitkat(data);
                } else {
                    handleAlbumBeforKitkat(data);
                }
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleAlbumOnKitkat(Intent data) {

        Uri dataUri = data.getData();
        String imagePath = null;

        if (DocumentsContract.isDocumentUri(this, dataUri)) {
            //document类型uri
            String documentId = DocumentsContract.getDocumentId(dataUri);
            if ("com.android.providers.media.documents".equals(dataUri.getAuthority())) {
                String[] split = documentId.split(":");
                String id = split[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(dataUri.getAuthority())) {
                Uri uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(uri, null);
            }
        } else if ("content".equalsIgnoreCase(dataUri.getScheme())) {
            //content 类型uri
            imagePath = getImagePath(dataUri, null);
        } else if ("file".equalsIgnoreCase(dataUri.getScheme())) {
            imagePath = dataUri.getPath();
        }
        displayPic(imagePath);
    }

    private void handleAlbumBeforKitkat(Intent data) {
        Uri data1 = data.getData();
        String imagePath = getImagePath(data1, null);
        displayPic(imagePath);
    }

    private void displayPic(String imagePath) {
        if (imagePath == null) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ivPic.setImageBitmap(bitmap);
        tvPicName.setText(imagePath);
    }

    private String getImagePath(Uri data1, String selection) {
        String pathStr = null;
        Cursor query = getContentResolver().query(data1, null, selection, null, null);
        if (query == null) {
            return null;
        }
        if (query.moveToFirst()) {
            pathStr = query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        query.close();
        return pathStr;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //camera
            case 200:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(captureIntent, 100);
                }
                break;
            //album
            case 300:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    ToastUtils.showInstance("please open write external storage permission");
                }
                break;
        }
    }

}
