package com.wf.aloha.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wf.aloha.R;
import com.wf.aloha.network.DkdPostService;
import com.wf.aloha.network.ProjectEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPostActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://www.daokoudai.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_post);

        initData();
    }

    private void initData() {
        //1 create Retrofit object

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        Retrofit postRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();


        //2 create API request
        DkdPostService dkdPostService = postRetrofit.create(DkdPostService.class);
        HashMap<String, String> mapParams = collectParams();
        Call<ProjectEntry> postCall = dkdPostService.getProjectList(mapParams);

        //3 send request
        postCall.enqueue(new Callback<ProjectEntry>() {
            @Override
            public void onResponse(Call<ProjectEntry> call, Response<ProjectEntry> response) {
                ProjectEntry body = response.body();
                if (body.isSuccess()) {
                    Log.d("-----", body.toString());
                }
            }

            @Override
            public void onFailure(Call<ProjectEntry> call, Throwable t) {
                Log.d("-----", t.toString());
            }
        });
    }

    private HashMap<String, String> collectParams() {
//        {"page":1,"size":8,"projectStatus":"1;2;3;4;5","coreEnterpriseId":0,"rate":0,"period":0,"loanTypeId":0}
        JSONObject mJsonObj = new JSONObject();
        try {
            mJsonObj.put("page", 1);
            mJsonObj.put("size", 8);
            mJsonObj.put("projectStatus", "1;2;3;4;5");
            mJsonObj.put("coreEnterpriseId", 0);
            mJsonObj.put("rate", 0);
            mJsonObj.put("period", 0);
            mJsonObj.put("loanTypeId", 0);
            HashMap<String, String> map = new HashMap<>();
            map.clear();
            map.put("params", mJsonObj.toString());
            map.put("appKey", "88888888");
            map.put("from", "AC");
            map.put("imei", "b9f38d2c226351c7");
            map.put("version", "1.4.0");
            map.put("apiName", "");
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
