package com.example.lib_track;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 16:43
 */
public class KyCatManager {
    private final Map<Object, Long> timeMap;

    private static class HOLDER {
        private static KyCatManager instance = new KyCatManager();
    }

    public static KyCatManager getInstance() {
        return HOLDER.instance;
    }

    public KyCatManager() {
        timeMap = new HashMap<>();
    }

    public void enterPage(Object obj) {
        if (obj != null && !timeMap.containsKey(obj)) {
            timeMap.put(obj, System.currentTimeMillis());
        }
    }

    public void leavePage(Object obj) {
        if (obj != null && timeMap.containsKey(obj)) {
            long enterTime = timeMap.get(obj);
            if (enterTime > 0) {
                long time = Long.parseLong(String.valueOf(System.currentTimeMillis() - enterTime));
                //todo 时长埋点 后台线程上传
                if(obj instanceof Activity){
                   String activityName = ((Activity)obj).getLocalClassName();

                    Log.d(KyConst.TAG, "time = " + time + ",name = " + activityName);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(activityName,time);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    KyTrackManager.get().onEvent("PV",jsonObject);
                }
            }
            timeMap.remove(obj);
        }
    }
}
