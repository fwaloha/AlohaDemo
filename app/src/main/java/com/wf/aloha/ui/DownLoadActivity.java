package com.wf.aloha.ui;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.wf.aloha.R;
import com.wf.aloha.network.NetApi;
import com.wf.aloha.network.NetService;
import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * retrofit 下载文件 并写入文件 打开pdf
 */
public class DownLoadActivity extends AppCompatActivity {

    private static final int STORAGE_REQUEST = 100;
    @BindView(R.id.pb_download)
    ProgressBar pbDownload;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.pdf_view)
    PDFView pdfView;
    private String newPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        ButterKnife.bind(this);

        checkSDcardPermission();

        doDownLoad();
//        copyFile();
        String url = "http://www.egmicro.com/download/EG3013_datasheet.pdf";
//        File file = new File(getExternalFilesDir(null) + File.separator + "chip4.pdf");
//        FileDownloader.getImpl().create(url)
//                .setPath(file.getPath())
//                .setListener(new FileDownloadListener() {
//                    @Override
//                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//                    }
//
//                    @Override
//                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                        pbDownload.setProgress(soFarBytes / totalBytes * 100);
//                        LogUtils.d("-----", totalBytes + "::" + soFarBytes);
//                    }
//
//                    @Override
//                    protected void completed(BaseDownloadTask task) {
//                        ToastUtils.showInstance("download finished!");
//                    }
//
//                    @Override
//                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//                    }
//
//                    @Override
//                    protected void error(BaseDownloadTask task, Throwable e) {
//
//                    }
//
//                    @Override
//                    protected void warn(BaseDownloadTask task) {
//
//                    }
//                }).start();
    }

    //文件复制 test
    private void copyFile() {
        File srcFile = new File(getExternalFilesDir(null) + File.separator + "datachip.pdf");
        File cpFile = new File(getExternalFilesDir(null) + File.separator + "copyChip.pdf");
        newPath = getExternalFilesDir(null) + File.separator + "copyChip.pdf";
        if (cpFile.exists()) {
            cpFile.delete();
            newPath = getExternalFilesDir(null) + File.separator + "copyChip1.pdf";
        }

        InputStream input;
        OutputStream output;
        try {
            input = new FileInputStream(getExternalFilesDir(null) + File.separator + "datachip.pdf");
            output = new FileOutputStream(newPath);

            byte[] buf = new byte[4096];
            int byteread;
            while ((byteread = input.read(buf)) > 0) {
                output.write(buf, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    //自己使用retrofit下载
    private void doDownLoad() {
        NetApi api = NetApi.getDownloadApi();
        NetService service = api.getService();
        final Call<ResponseBody> call = service.downloadFile();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    writeToDisk(body);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }

        });
    }

    //写入到文件
    private void writeToDisk(ResponseBody body) {
        String externalStorageState = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
            ToastUtils.showInstance("存储异常！请检查");
        }
//        File filePath = Environment.getExternalStorageDirectory();
//        LogUtils.d("-----", filePath.toString());
        File file = new File(getExternalFilesDir(null) + File.separator + "chip.pdf");
        LogUtils.d("-----2", file.toString());
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] fileReader = new byte[4096];

        final long fileSize = body.contentLength();
        LogUtils.d("-----", "filesize=" + fileSize);
        long fileDownloadSize = 0;
        try {
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(getExternalFilesDir(null) + File.separator + "chip_retrofit3.pdf");

            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileDownloadSize += read;
                pbDownload.setProgress((int) (fileDownloadSize * 100 / fileSize));
                final long finalFileDownloadSize = fileDownloadSize;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvDownload.setText("正在下载...      " + fileSize / 1024 + "k/" + finalFileDownloadSize / 1024 + "k");
                    }
                });
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //1，借助手机app打开pdf
            File downlaodfile = new File(getExternalFilesDir(null) + File.separator + "chip_retrofit3.pdf");
            if (!downlaodfile.exists()) {
                ToastUtils.showInstance("重新下载文件");
                return;
            }
            getExternalCacheDir();
            //pdf 筛选器
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(downlaodfile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            //此处不能写筛选器，否则会崩溃ActivityNotFoundException。只能下面startactivity的写法
//            Intent.createChooser(intent,"select one PDF reader");

            try {
                startActivity(Intent.createChooser(intent, "select pdf reader"));
            } catch (ActivityNotFoundException ex) {
                ToastUtils.showInstance("Please install a pdf Manager.");
            }

            //2，应用第三方插件在app内部打开pdf
            pdfView.fromFile(downlaodfile).load();
        }


//        if (file.exists()) {
//            file.delete();
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            fileSize = body.bytes().length;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        body.contentLength();
    }

    private void checkSDcardPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        String[] PERMISSION_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, STORAGE_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ToastUtils.showInstance("ok！");
            } else {
                ToastUtils.showInstance("请开启访问内部存储权限！");
            }
        }
    }
}
