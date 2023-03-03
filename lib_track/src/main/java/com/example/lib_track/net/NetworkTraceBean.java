package com.example.lib_track.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 17:35
 */
public class NetworkTraceBean {

    public static String CALL_START = "callStart";
    public static String CALL_END = "callEnd";
    public static String DNS_START = "dnsStart";
    public static String DNS_END = "dnsEnd";
    public static String CONNECT_START = "connectStart";
    public static String SECURE_CONNECT_START = "secureConnectStart";
    public static String SECURE_CONNECT_END = "secureConnectEnd";
    public static String CONNECT_END = "connectEnd";
    public static String REQUEST_BODY_START = "requestBodyStart";
    public static String REQUEST_BODY_END = "requestBodyEnd";
    public static String REQUEST_HEADERS_START = "requestHeadersStart";
    public static String REQUEST_HEADERS_END = "requestHeadersEnd";
    public static String RESPONSE_HEADERS_START = "responseHeadersStart";
    public static String RESPONSE_HEADERS_END = "responseHeadersEnd";
    public static String RESPONSE_BODY_START = "responseBodyStart";
    public static String RESPONSE_BODY_END = "responseBodyEnd";
    public static String TRACE_NAME_TOTAL = "Total Time";
    public static String TRACE_NAME_DNS = "DNS";
    public static String TRACE_NAME_SECURE_CONNECT = "Secure Connect";
    public static String TRACE_NAME_CONNECT = "Connect";
    public static String TRACE_NAME_REQUEST_HEADERS = "Request Headers";
    public static String TRACE_NAME_REQUEST_BODY = "Request Body";
    public static String TRACE_NAME_RESPONSE_HEADERS = "Response Headers";
    public static String TRACE_NAME_RESPONSE_BODY = "Response Body";

    public static String TRACE_PARAMS_ID = "Params_id";
    public static String TRACE_PARAMS_URL = "Params_url";
    public static String TRACE_PARAMS_METHOD = "Params_method";
    public static String TRACE_PARAMS_TIME = "Params_time";
    public static String TRACE_PARAMS_SUCCESS = "Params_isSuccess";
    public static String TRACE_PARAMS_RESULT = "Params_result";
    public static String TRACE_PARAMS_CODE = "Params_code";


    private String id;
    private String url;
    private String method;
    private long time;
    private String exception;
    private Map<String, Long> networkEventsMap = new HashMap<>();
    private Map<String, Object> traceItemList = new HashMap<>();

    private Map<String,Object> traceParamsList = new HashMap<>();
    private int statusCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, Long> getNetworkEventsMap() {
        return networkEventsMap;
    }

    public void setNetworkEventsMap(Map<String, Long> networkEventsMap) {
        this.networkEventsMap = networkEventsMap;
    }

    public Map<String, Object> getTraceItemList() {
        return traceItemList;
    }

    public void setTraceItemList(Map<String, Object> traceItemList) {
        this.traceItemList = traceItemList;
    }

    public Map<String, Object> getTraceParamsList() {
        return traceParamsList;
    }

    public void setTraceParamsList(Map<String, Object> traceParamsList) {
        this.traceParamsList = traceParamsList;
    }
}
