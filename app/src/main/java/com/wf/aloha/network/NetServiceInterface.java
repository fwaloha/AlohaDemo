package com.wf.aloha.network;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * Created by wangfei on 2017/5/24.
 */

public interface NetServiceInterface {
    //获取dkd项目list  如果用filedmap参数必须用formurlencoded
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
    
    //上传照片
    @Multipart
    @POST("api/2.0/baidu/uploadImg")
    Call<UploadEntry> uploadPic(@PartMap Map<String, String> partMap, @Part MultipartBody.Part file );

    //获取电话号码归属地
    @GET("/netpopo/shouji/query")
    Observable<PhoneNumEntry> getPhonelocal(@Header("apikey") String apikey, @Query("shouji") String phone);
}
