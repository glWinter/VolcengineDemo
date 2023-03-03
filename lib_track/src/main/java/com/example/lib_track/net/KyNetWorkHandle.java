package com.example.lib_track.net;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 17:37
 */
public class KyNetWorkHandle {
    private Map<String, NetworkTraceBean> mTraceModelMap;

    private static class HOLDER{
        private static KyNetWorkHandle handle = new KyNetWorkHandle();
    }

    public static KyNetWorkHandle getInstance(){
        return HOLDER.handle;
    }


    public NetworkTraceBean getNetworkTraceModel(String id) {
        if (mTraceModelMap == null) {
            mTraceModelMap = new HashMap<>();
        }
        if (mTraceModelMap.containsKey(id)) {
            return mTraceModelMap.get(id);
        } else {
            NetworkTraceBean traceModel = new NetworkTraceBean();
            traceModel.setId(id);
            traceModel.setTime(System.currentTimeMillis());
            mTraceModelMap.put(id, traceModel);
            return traceModel;
        }
    }

    public Map<String, NetworkTraceBean> getTraceModelMap() {
        return mTraceModelMap;
    }

}
