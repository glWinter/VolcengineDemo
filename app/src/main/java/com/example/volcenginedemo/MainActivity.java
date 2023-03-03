package com.example.volcenginedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.lib_track.IKyPageMeta;
import com.example.lib_track.KyTrackManager;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements IKyPageMeta {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONObject props = new JSONObject();
        try {
            props.put("key", "value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        KyTrackManager.get().login("uniqueId");
        KyTrackManager.get().trackClick(new View(this),props);
        KyTrackManager.get().setViewId(new View(this),"");
        KyTrackManager.get().setViewProperties(new View(this),new JSONObject());
        KyTrackManager.get().ignoreAutoTrackPage();
        KyTrackManager.get().ignoreAutoTrackClickByViewType();
        KyTrackManager.get().ignoreAutoTrackClick(new View(this));
    }

    @Override
    public String path() {
        return "MainActivity_path";
    }

    @Override
    public String title() {
        return "MainActivity_title";
    }

    @Override
    public JSONObject pageProperties() {
        JSONObject params = new JSONObject();
        try {
            params.put("key", "value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }
}