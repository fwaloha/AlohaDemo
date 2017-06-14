package com.wf.aloha.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 相关的API
 */
public class NetApi {

    /**
     * HOST地址
     */
//    测试下载PDF地址
//    public static final String BASE_URL = "http://www.egmicro.com/";
    public static final String BASE_URL = "http://www.daokoudai.com/";
//    public static final String BASE_URL = "http://www.egmicro.com/";
    /**
     * 开发者Key
     */
    public static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";

    /**
     * 获取Api实例
     *
     * @return
     */
    public static NetApi getApi() {
        return ApiHolder.netApi;
    }
    
    public static NetApi getDownloadApi() {
        return ApiDownloadHolder.downloadApi;
    }

    static class ApiHolder {
        private static NetApi netApi = new NetApi(false);
    }
    static class ApiDownloadHolder {
        private static NetApi downloadApi = new NetApi(true);
    }

    private NetService service;

    protected NetApi(boolean isDownload) {

        //设置超时
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        //下载文件的方式。加了callbackexecutor 指定callback回调在子线程，否则默认在主线程
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Retrofit downlaod_retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .callbackExecutor(executorService)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        if (isDownload) {
            service = downlaod_retrofit.create(NetService.class);
        } else {
            service = retrofit.create(NetService.class);
        }
    }

    /**
     * 获取service实例
     *
     * @return
     */
    public NetService getService() {
        return service;
    }
}
