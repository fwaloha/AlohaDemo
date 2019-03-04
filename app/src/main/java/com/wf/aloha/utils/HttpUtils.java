package com.wf.aloha.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {

    private static HttpURLConnection urlConnection;

    public static void sendGetRequestHttp(final String url, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlPath = new URL(url);
                    urlConnection = (HttpURLConnection) urlPath.openConnection();
                    urlConnection.setConnectTimeout(10000);
                    urlConnection.setReadTimeout(10000);
                    StringBuffer stringBuffer = new StringBuffer();
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(stringBuffer.toString().trim());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onError(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onError(e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();

    }

    public static void sendGetRequestOk(final String url, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(build).enqueue(callback);
    }
}
