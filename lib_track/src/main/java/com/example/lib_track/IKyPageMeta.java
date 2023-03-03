package com.example.lib_track;

import com.bytedance.applog.IPageMeta;

import org.json.JSONObject;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/27 17:09
 */
public interface  IKyPageMeta extends IPageMeta {

    @Override
    String path();

    @Override
    String title();

    @Override
    JSONObject pageProperties();
}
