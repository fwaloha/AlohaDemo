package com.wf.aloha.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wf.aloha.R;
import com.wf.aloha.RtfRequestInterfaceTest;
import com.wf.aloha.bean.PostFileBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RtfTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtf_test);


        //1 create build
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        

        //2,create request
        RtfRequestInterfaceTest interfaceTest = retrofit.create(RtfRequestInterfaceTest.class);
        HashMap<String, String> params = collectParams();
//        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"),new File(""));
        RequestBody file = RequestBody.create(MediaType.parse("image/png"), new File(""));

        MultipartBody.Part file1 = MultipartBody.Part.createFormData("files", "ID_CARD_FACE_IN_HAND.JPEG", file);
        MultipartBody.Part file2 = MultipartBody.Part.createFormData("files", "ID_CARD_BACK_IN_HAND.JPEG", file);

        ArrayList<MultipartBody.Part> fileLists = new ArrayList<>();
        fileLists.add(file1);
        fileLists.add(file2);
        Call<PostFileBean> uploadCall = interfaceTest.uploadFile(params, fileLists);

        //3.异步请求
        uploadCall.enqueue(new Callback<PostFileBean>() {
            @Override
            public void onResponse(Call<PostFileBean> call, Response<PostFileBean> response) {

            }

            @Override
            public void onFailure(Call<PostFileBean> call, Throwable t) {

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
            map.put("imei", "6320bc8aba18a22c");
            map.put("version", "1.4.6");
            map.put("apiName", "");
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
