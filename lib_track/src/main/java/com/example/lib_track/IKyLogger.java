package com.example.lib_track;

/**
 * @author gaolei46@ztoky.cn
 * 2023/3/2 09:13
 */
public interface IKyLogger {

    boolean isShowLog = false;
    boolean isShowStackTrace = false;
    String defaultTag = KyConst.TAG;

    void showLog(boolean isShowLog);

    void showStackTrace(boolean isShowStackTrace);

    void debug(String tag, String message);

    void info(String tag, String message);

    void warning(String tag, String message);

    void error(String tag, String message);

    void error(String tag, String message, Throwable e);

    void monitor(String message);

    boolean isMonitorMode();

    String getDefaultTag();
}
