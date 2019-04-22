package com.wf.aloha.control;

import android.os.AsyncTask;
import android.os.Environment;

import com.wf.aloha.listener.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int DOWNLOAD_SUCCESS = 1;
    private static final int DOWNLOAD_CANCELED = 2;
    private static final int DOWNLOAD_PAUSE = 3;
    private final DownloadListener listener;
    private InputStream inputStream;
    private boolean isCancelled;
    private boolean isPause;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        long contentLenth = getContentLenth(strings[0]);
        long downloadedLength = 0;
        String fileName = strings[0].substring(strings[0].lastIndexOf("/"));
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(filePath, fileName);
        if (file.exists()) {
            downloadedLength = file.length();
            if (contentLenth > 0 && downloadedLength == contentLenth) {
                return DOWNLOAD_SUCCESS;
            }
        }
        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
            if (downloadedLength > 0) {
                accessFile.seek(downloadedLength);
            }
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            inputStream = response.body().byteStream();
            if (response != null) {
                byte[] bytes = new byte[1024];
                int len = 0;
                int total = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    if (isCancelled) {
                        return DOWNLOAD_CANCELED;
                    } else if (isPause) {
                        return DOWNLOAD_PAUSE;
                    } else {
                        total += len;
                        accessFile.write(bytes, 0, len);
                        int progress = (int) ((total + downloadedLength) * 100 / contentLenth);
                        publishProgress(progress);
                    }
                }
            }
            response.body().close();
            accessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isCancelled&& file!=null){
                file.delete();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case DOWNLOAD_SUCCESS:
                listener.onSuccess();
                break;
            case DOWNLOAD_PAUSE:
                listener.onPause();
                break;
            case DOWNLOAD_CANCELED:
                listener.onCanceled();
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    public void pauseDownload() {
        isPause = true;
    }

    public void cancelDownload() {
        isCancelled = true;
    }

    private long getContentLenth(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long lenth = response.body().contentLength();
                response.close();
                return lenth;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0l;
    }
}
