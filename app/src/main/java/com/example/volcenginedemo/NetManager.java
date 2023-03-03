package com.example.volcenginedemo;

import androidx.annotation.NonNull;

import com.example.lib_track.net.NetworkListener;

import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 14:52
 */
public class NetManager {
    private static class Holder{
        private static NetManager instance = new NetManager();
    }
    public static NetManager getInstance(){
        return  Holder.instance;
    }

    Retrofit retrofit;

    private NetManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.eventListenerFactory(NetworkListener.get());
        OkHttpClient client = builder.build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.wanandroid.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .build();
    }

}
