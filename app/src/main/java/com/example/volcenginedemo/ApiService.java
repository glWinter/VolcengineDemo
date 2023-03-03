package com.example.volcenginedemo;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/23 17:33
 */
public interface ApiService {

 @GET("banner/json")
 Call<JsonObject> getBanner();

}
