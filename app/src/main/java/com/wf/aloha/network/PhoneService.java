package com.wf.aloha.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by wangfei on 2017/5/19.
 */

public interface PhoneService {

    @GET("/netpopo/shouji/query")
    Call<PhoneNumEntry> getResult(@Header("apikey") String apikey, @Query("shouji") String phone);
}
