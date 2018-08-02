package com.wf.aloha;

import com.wf.aloha.bean.BaseRtfBean;
import com.wf.aloha.bean.PostFileBean;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface RtfRequestInterfaceTest {

    @POST("")
    @Multipart
    Call<PostFileBean> uploadFile(@PartMap HashMap<String, String> params, List<MultipartBody.Part> lists);
}
