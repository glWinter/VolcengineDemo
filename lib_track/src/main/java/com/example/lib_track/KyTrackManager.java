package com.example.lib_track;

import android.content.Context;
import android.view.View;

import com.bytedance.applog.AppLog;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.util.UriConstants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/27 16:25
 */
public class KyTrackManager implements IKyTrackManager {
    public static IKyLogger logger = new KyLogger(KyConst.TAG);
    private static class Holder {
        private static KyTrackManager kyTrackManager = new KyTrackManager();
    }

    public static IKyTrackManager get() {
        return Holder.kyTrackManager;
    }
    /**
     * 火山SDK初始化
     *
     * @param context
     * @param kyInitializeConfig
     */
    @Override
    public void init(Context context, KyInitializeConfig kyInitializeConfig) {
        logger.debug(KyConst.TAG,"KyTrack SDK init");
        final InitConfig config = new InitConfig(kyInitializeConfig.getAppId(), kyInitializeConfig.getChannel());
        config.setUriConfig(UriConfig.createByDomain(kyInitializeConfig.getUrl(),null));


        config.setAutoTrackEnabled(false);
        config.setH5CollectEnable(true);

        config.setAutoTrackFragmentEnabled(true);
        config.setLogEnable(kyInitializeConfig.isLogEnabled()); // true:开启日志，参考4.3节设置logger，false:关闭日志
        AppLog.setEncryptAndCompress(true); // 加密开关，true开启，false关闭
        AppLog.init(context, config);

        //开启pageListener
        if(kyInitializeConfig.isPageListenerEnabled()){
            logger.debug(KyConst.TAG,"KyTrack SDK open page listener");
            KyPageLifecycleManager.getInstance().startPageListener(context);
        }
    }

    @Override
    public void login(String uniqueId) {
        logger.debug(KyConst.TAG,"KyTrack login");
        AppLog.setUserUniqueID(uniqueId);
    }

    @Override
    public void logout() {
        logger.debug(KyConst.TAG,"KyTrack logout");
        AppLog.setUserUniqueID(null);
    }

    @Override
    public void setPublicParams(HashMap<String, Object> maps) {
        logger.debug(KyConst.TAG,"KyTrack set public header");
        AppLog.setHeaderInfo(maps);
    }

    @Override
    public void removeParams(String key) {
        logger.debug(KyConst.TAG,"KyTrack remove params");
        AppLog.removeHeaderInfo(key);
    }

    @Override
    public void onEvent(String event) {
        logger.debug(KyConst.TAG,"KyTrack send user event");
        AppLog.onEventV3(event);
    }

    @Override
    public void onEvent(String event, JSONObject jsonObject) {
        logger.debug(KyConst.TAG,"KyTrack send user event with params");
        AppLog.onEventV3(event, jsonObject);
    }

    @Override
    public void setViewId(View view, String viewId) {
        logger.debug(KyConst.TAG,"KyTrack set viewId");
        AppLog.setViewId(view, viewId);
    }

    @Override
    public void setViewProperties(View view, JSONObject jsonObject) {
        logger.debug(KyConst.TAG,"KyTrack set view params");
        AppLog.setViewProperties(view, jsonObject);
    }

    @Override
    public void trackPage() {
//        AppLog.trackPage();
    }

    @Override
    public void trackClick(View view, JSONObject jsonObject) {
        logger.debug(KyConst.TAG,"KyTrack set view click");
        AppLog.trackClick(view,jsonObject);
    }

    @Override
    public void ignoreAutoTrackPage(Class<?>... classes) {
        logger.debug(KyConst.TAG,"KyTrack set ignore classes");
        AppLog.ignoreAutoTrackPage(classes);
    }

    @Override
    public void ignoreAutoTrackClick(View view) {
        logger.debug(KyConst.TAG,"KyTrack set ignore view");
        AppLog.ignoreAutoTrackClick(view);
    }

    @Override
    public void ignoreAutoTrackClickByViewType(Class<?>... classes) {
        logger.debug(KyConst.TAG,"KyTrack set ignore view class");
        AppLog.ignoreAutoTrackClickByViewType(classes);
    }

    public static synchronized void openLog() {
        logger.showLog(true);
        logger.info(KyConst.TAG, "KyTrack SDK openLog");
    }
}
