package com.example.lib_track.net;

import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lib_track.KyConst;
import com.example.lib_track.KyTrackManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 17:15
 */
public class NetworkListener extends EventListener {

    private static AtomicInteger mNextRequestId = new AtomicInteger(0);
    private String mRequestId;

    public static Factory get() {
        return call -> new NetworkListener();
    }


    @Override
    public void callStart(@NonNull Call call) {
        super.callStart(call);
        mRequestId = String.valueOf(mNextRequestId.getAndIncrement());
        saveEvent(NetworkTraceBean.CALL_START);
        saveUrl(call.request().url().toString());
        saveMethod(call.request().method());
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        super.callFailed(call, ioe);
        saveEvent(NetworkTraceBean.CALL_END);
        saveException(ioe.getMessage());
        generateTraceData(false);
    }

    @Override
    public void callEnd(@NonNull Call call) {
        super.callEnd(call);
        saveEvent(NetworkTraceBean.CALL_END);
        generateTraceData(true);
    }

    @Override
    public void dnsStart(@NonNull Call call, @NonNull String domainName) {
        super.dnsStart(call, domainName);
        saveEvent(NetworkTraceBean.DNS_START);
    }


    @Override
    public void dnsEnd(@NonNull Call call, @NonNull String domainName, @NonNull List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        saveEvent(NetworkTraceBean.DNS_END);
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        saveEvent(NetworkTraceBean.CONNECT_START);
    }

    @Override
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
        saveEvent(NetworkTraceBean.SECURE_CONNECT_START);
    }

    @Override
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        saveEvent(NetworkTraceBean.SECURE_CONNECT_END);
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        saveEvent(NetworkTraceBean.CONNECT_END);
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
        saveEvent(NetworkTraceBean.CONNECT_END);
    }

    @Override
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
        saveEvent(NetworkTraceBean.REQUEST_HEADERS_START);
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
        saveEvent(NetworkTraceBean.REQUEST_HEADERS_END);
    }

    @Override
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
        saveEvent(NetworkTraceBean.REQUEST_BODY_START);
    }

    @Override
    public void requestBodyEnd(Call call, long byteCount) {
        super.requestBodyEnd(call, byteCount);
        saveEvent(NetworkTraceBean.REQUEST_BODY_END);
    }

    @Override
    public void responseHeadersStart(Call call) {
        super.responseHeadersStart(call);
        saveEvent(NetworkTraceBean.RESPONSE_HEADERS_START);
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        super.responseHeadersEnd(call, response);
        saveEvent(NetworkTraceBean.RESPONSE_HEADERS_END);
        saveStatusCode(response.code());
    }

    @Override
    public void responseBodyStart(Call call) {
        super.responseBodyStart(call);
        saveEvent(NetworkTraceBean.RESPONSE_BODY_START);
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        super.responseBodyEnd(call, byteCount);
        saveEvent(NetworkTraceBean.RESPONSE_BODY_END);
    }

    private void saveMethod(String method) {
        NetworkTraceBean networkTraceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        if (networkTraceModel == null) {
            return;
        }
        networkTraceModel.setMethod(method);
    }

    private void saveUrl(String url) {
        NetworkTraceBean networkTraceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        networkTraceModel.setUrl(url);
    }

    private void saveException(String exception){
        NetworkTraceBean networkTraceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        networkTraceModel.setException(exception);
    }
    private void saveEvent(String eventName) {
        NetworkTraceBean networkTraceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        if (networkTraceModel == null) {
            return;
        }
        Map<String, Long> networkEventsMap = networkTraceModel.getNetworkEventsMap();
        networkEventsMap.put(eventName, SystemClock.elapsedRealtime());
    }

    private void saveStatusCode(int statusCode) {
        NetworkTraceBean networkTraceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        networkTraceModel.setStatusCode(statusCode);
    }

    private void generateTraceData(boolean isSuccess) {
        NetworkTraceBean traceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        if (traceModel == null) {
            return;
        }
        Map<String, Long> eventsTimeMap = traceModel.getNetworkEventsMap();
        Map<String, Object> traceList = traceModel.getTraceItemList();
        traceList.put(NetworkTraceBean.TRACE_NAME_TOTAL, getEventCostTime(eventsTimeMap, NetworkTraceBean.CALL_START, NetworkTraceBean.CALL_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_DNS, getEventCostTime(eventsTimeMap, NetworkTraceBean.DNS_START, NetworkTraceBean.DNS_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_SECURE_CONNECT, getEventCostTime(eventsTimeMap, NetworkTraceBean.SECURE_CONNECT_START, NetworkTraceBean.SECURE_CONNECT_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_CONNECT, getEventCostTime(eventsTimeMap, NetworkTraceBean.CONNECT_START, NetworkTraceBean.CONNECT_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_REQUEST_HEADERS, getEventCostTime(eventsTimeMap, NetworkTraceBean.REQUEST_HEADERS_START, NetworkTraceBean.REQUEST_HEADERS_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_REQUEST_BODY, getEventCostTime(eventsTimeMap, NetworkTraceBean.REQUEST_BODY_START, NetworkTraceBean.REQUEST_BODY_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_RESPONSE_HEADERS, getEventCostTime(eventsTimeMap, NetworkTraceBean.RESPONSE_HEADERS_START, NetworkTraceBean.RESPONSE_HEADERS_END));
        traceList.put(NetworkTraceBean.TRACE_NAME_RESPONSE_BODY, getEventCostTime(eventsTimeMap, NetworkTraceBean.RESPONSE_BODY_START, NetworkTraceBean.RESPONSE_BODY_END));
        Map<String, Object> traceParamsList = traceModel.getTraceParamsList();
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_ID,traceModel.getId());
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_URL,traceModel.getUrl());
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_METHOD,traceModel.getMethod());
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_TIME,traceModel.getTime());
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_CODE,traceModel.getStatusCode());
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_SUCCESS,isSuccess);
        traceParamsList.put(NetworkTraceBean.TRACE_PARAMS_RESULT,isSuccess?"success":traceModel.getException());
        sendCat();
    }

    private void sendCat() {
        NetworkTraceBean traceModel = KyNetWorkHandle.getInstance().getNetworkTraceModel(mRequestId);
        Map<String, Object> traceItemList = traceModel.getTraceItemList();
        Map<String, Object> traceParamsList = traceModel.getTraceParamsList();
        traceItemList.putAll(traceParamsList);
        String params = new Gson().toJson(traceItemList);
        KyTrackManager.logger.debug(KyConst.TAG,params);
        try {
            JSONObject jsonObject = new JSONObject(params);
            KyTrackManager.get().onEvent(traceModel.getUrl(), jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public static long getEventCostTime(Map<String, Long> eventsTimeMap, String startName, String endName) {
        if (!eventsTimeMap.containsKey(startName) || !eventsTimeMap.containsKey(endName)) {
            return 0;
        }
        Long endTime = eventsTimeMap.get(endName);
        Long start = eventsTimeMap.get(startName);
        return endTime - start;
    }
}
