package com.wf.aloha.network;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wangfei on 2017/5/23.
 */

public class RetrofitNetWork<T> {

    private static Retrofit postRetrofit;

    public static void createRetrofit(String baseUrl) {
        postRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static void createPostRequest(Object obj) {
        DkdPostService dkdPostService = postRetrofit.create(DkdPostService.class);
        PostService postService = postRetrofit.create(PostService.class);
//        HashMap<String, String> mapParams = collectParams();
//        Call<ProjectEntry> postCall = postService.getPostResult(mapParams);
//        return postCall;
//        Call<ProjectEntry> postCall = dkdPostService.getProjectList(mapParams);
    }

    public static void doPostRequest(String baseUrl,String url,ProjectEntry resultBean) {
        //1, create retrofit
        createRetrofit(baseUrl);
        //2, create API request
//        Call<ProjectEntry> postCall = createPostRequest(resultBean);
        //3, do in back

    }

    public interface PostService {
        @FormUrlEncoded
        @POST("/api/2.0/baidu/getProjectList")
        Call<ProjectEntry> getPostResult(@FieldMap Map<String, String> mapParams);
    }
}
