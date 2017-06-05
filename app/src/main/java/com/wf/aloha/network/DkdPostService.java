package com.wf.aloha.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wangfei on 2017/5/22.
 */

public interface DkdPostService {
    @FormUrlEncoded
    @POST("/api/2.0/baidu/getProjectList")
    Call<ProjectEntry> getProjectList(@FieldMap Map<String, String> mapParams);
}
