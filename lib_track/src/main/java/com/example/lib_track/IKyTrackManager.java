package com.example.lib_track;

import android.content.Context;
import android.view.View;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/27 17:15
 */
public interface IKyTrackManager {
    /**
     * sdk 初始化
     * @param context
     * @param kyInitializeConfig
     */
    void init(Context context, KyInitializeConfig kyInitializeConfig);

    /**
     * 设置您账号体系的ID, 并保证其唯一性
     * @param uniqueId
     */
    void login(String uniqueId);

    /**
     * 登出账号
     */
    void logout();

    /**
     * 设置公共参数
     * @param maps
     */
    void setPublicParams(HashMap<String, Object> maps);

    /**
     * 移除公共参数
     * @param key
     */
    void removeParams(String key);

    /**
     * 上报事件event，该事件不包含属性
     * @param event
     */
    void onEvent(String event);

    /**
     * 上报事件event，该事件包含属性
     * @param event
     * @param jsonObject
     */
    void onEvent(String event, JSONObject jsonObject);

    /**
     * 指定的View对象设置一个元素ID，字符串即可
     * @param view
     * @param viewId
     */
    void setViewId(View view, String viewId);

    /**
     * 对指定的View对象设置属性值
     * @param view
     * @param jsonObject
     */
    void setViewProperties(View view, JSONObject jsonObject);

    void trackPage();

    void trackClick(View view, JSONObject jsonObject);

    /**
     * 忽略特定的Activity或Fragment的全埋点事件
     * @param classes
     */
    void ignoreAutoTrackPage(Class<?>... classes);

    /**
     * 忽略特定View的点击事件
     * @param view
     */
    void ignoreAutoTrackClick(View view);

    /**
     * 忽略指定View类型的点击事件
     * @param classes
     */
    void ignoreAutoTrackClickByViewType(Class<?>... classes);

}
