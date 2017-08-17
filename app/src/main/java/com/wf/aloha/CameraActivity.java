package com.wf.aloha;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.NougatTools;
import com.wf.aloha.utils.ToastUtils;
import com.youtu.Youtu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
    @BindView(R.id.bt_capture)
    Button mBtCapture;
    @BindView(R.id.sf_view)
    SurfaceView sfView;
    private int mWidth;
    private int mHeight;
    private Camera mCamera;

    public static final String APP_ID = "10087528";
    public static final String SECRET_ID = "AKID1we8RXT3TgjaDc9jRvEJAoEQN6bs7lPp";
    public static final String SECRET_KEY = "JOFsGQL2cy61iARXNxJrCJAl7E6j7GAp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

//        ckPermission();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        mRectOnCamera.setIAutoFocus(this);
//        takePicBtn.setOnClickListener(this);
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

    @OnClick({R.id.bt_dialog, R.id.bt_capture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_dialog:
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
                break;
            case R.id.bt_capture:
                //Todo take a picture
                mCamera.takePicture(null, null, mPictureCallback);
                break;
        }
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
//        selectCamera();
        mCamera = getCamera();
        setStartPreview(mCamera, sfView.getHolder());
    }

    /**
     * 动态权限检查
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUST_CODE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    mCamera = getCamera();
                    setStartPreview(mCamera, sfView.getHolder());
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

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkCameraHardware(this)) {
            ToastUtils.showInstance("您的设备不支持拍照！");
            return;
        }
//        ckPermission();
    }

    /**
     * Check if this device has a camera
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * Camera回调，通过data[]保持图片数据信息
     */
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
//            File pictureFile = getOutputMediaFile();

            final File capPath = new File(getExternalFilesDir(null) + File.separator + System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream fos = new FileOutputStream(capPath);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            final Youtu youtu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject = youtu.IdCardOcr(capPath.getAbsolutePath(), 0);
//                        JSONObject jsonObject = youtu.IdCardOcrUrl("http://img.130158.com/uploads/i_3_5136495x3945925686_23.jpg", 0);
                        LogUtils.d("-----", jsonObject.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }

            }).start();


            //            if (pictureFile == null) {
//                return;
//            }
//            try {
//                FileOutputStream fos = new FileOutputStream(pictureFile);
//                fos.write(data);
//                fos.close();
//                Intent intent = new Intent(CustomCamera.this, CameraResult.class);
//                intent.putExtra("picPath", pictureFile.getAbsolutePath());
//                startActivity(intent);
//                CustomCamera.this.finish();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    };

    private void setStartPreview(final Camera mCamera, SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(0);
            mCamera.startPreview();
            mCamera.cancelAutoFocus();


            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPictureFormat(ImageFormat.JPEG);
            List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();
            for (Camera.Size size : pictureSizes) {
                LogUtils.d("-----", size.width + "::" + size.height);
                parameters.setPictureSize(size.width, size.height);//设置为最高拍摄质量
                break;
            }
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            mCamera.setParameters(parameters);
//            mCamera.autoFocus(new Camera.AutoFocusCallback() {
//                
//                
//
//                @Override
//                public void onAutoFocus(boolean success, Camera camera) {
////                    mCamera.takePicture(null, null, mPictureCallback);
////                    mCamera.startPreview();
//                    if (success) {
//                        ToastUtils.showInstance("focus ok");
//                    }
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera getCamera() {
        try {
            Camera camera = Camera.open();
            return camera;
        } catch (Exception e) {
            return null;
        }
    }
}
