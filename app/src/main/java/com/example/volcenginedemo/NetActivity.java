package com.example.volcenginedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lib_track.KyCatManager;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 14:52
 */
public class NetActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.net_test).setOnClickListener(v->startActivity(new Intent(this,SecondActivity.class)));



    }
}


/**
 *  NetManager.getInstance().retrofit.create(ApiService.class)
 *                 .getBanner().enqueue(new Callback<JsonObject>() {
 *                     @Override
 *                     public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
 *
 *                     }
 *
 *                     @Override
 *                     public void onFailure(Call<JsonObject> call, Throwable t) {
 *                         Log.d("test","t = "+t.getMessage());
 *                     }
 *                 })
 */