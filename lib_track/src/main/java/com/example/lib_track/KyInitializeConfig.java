package com.example.lib_track;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/27 16:26
 */
public class KyInitializeConfig {
    private String appId;
    private String channel;
    private boolean logEnabled;
    private boolean pageListenerEnabled;
    private String url;

    public KyInitializeConfig(Builder builder) {
        appId = builder.appId;
        channel = builder.channel;
        logEnabled = builder.logEnabled;
        pageListenerEnabled = builder.pageListenerEnabled;
        url = builder.url;
        if(logEnabled){
            KyTrackManager.openLog();
        }
    }

    public boolean isPageListenerEnabled() {
        return pageListenerEnabled;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public String getAppId() {
        return appId;
    }

    public String getChannel() {
        return channel;
    }

    public String getUrl(){
        return url;
    }

    public static class Builder {
        private String appId;
        private String channel;
        private boolean logEnabled;
        private boolean pageListenerEnabled;
        private String url;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setLogEnabled(boolean logEnabled) {
            this.logEnabled = logEnabled;
            return this;
        }

        public Builder setAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder setChannel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder setPageEnabled(boolean pageListenerEnabled) {
            this.pageListenerEnabled = pageListenerEnabled;
            return this;
        }

        public KyInitializeConfig build() {

            return new KyInitializeConfig(this);
        }
    }
}
