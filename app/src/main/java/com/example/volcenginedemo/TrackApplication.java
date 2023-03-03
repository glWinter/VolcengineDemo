package com.example.volcenginedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lib_track.KyInitializeConfig;
import com.example.lib_track.KyTrackManager;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/27 16:24
 */
public class TrackApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        KyInitializeConfig config = new KyInitializeConfig.Builder()
                .setChannel("")
                .setAppId("")
                .setUrl("http://www.baidu.com")
                .setLogEnabled(true)
                .setPageEnabled(true)
                .build();
        KyTrackManager.get().init(this,config);


    }
}
