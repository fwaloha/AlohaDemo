package com.wf.aloha.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by wangfei on 2017/5/24.
 */

public interface NetService {
    //获取dkd项目list
    @FormUrlEncoded
    @POST("/api/2.0/baidu/getProjectList")
    Call<ProjectEntry> postProjectList(@FieldMap Map<String, String> mapParams);

    //获取电话号码归属地
    @GET("/netpopo/shouji/query")
    Observable<PhoneNumEntry> getResult(@Header("apikey") String apikey, @Query("shouji") String phone);
    
    //获取电话号码归属地
    @GET("/netpopo/shouji/query")
    Call<PhoneNumEntry> getPhoneLbs(@Header("apikey") String apikey, @Query("shouji") String phone);
    
    //下载
    @Streaming //它意味着立刻传递字节码，而不需要把整个文件读进内存。注明下载的是大文件否则retrofit会全部缓存到内存，可能会oom
    @GET("/download/EG3013_datasheet.pdf")
    Call<ResponseBody> downloadFile();
}
