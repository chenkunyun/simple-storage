package com.kchen.storage.common.util.web;


import com.kchen.storage.common.util.web.entity.TraceInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * web性能相关util
 */
public final class TraceUtil {

    public static String TRACE_INFO = "internalTraceInfo";    //保存请求的trace信息

    /**
     * 为请求分配一个traceId
     * @param request
     */
    public static String assignRequestTraceId(HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString().replace("-", "");
        getTraceInfo(request).setUuid(traceId);
        return traceId;
    }

    /**
     * 获取一个请求的traceId
     * @param request
     * @return
     */
    public static String getRequestTraceId(HttpServletRequest request) {
        return getTraceInfo(request).getUuid();
    }

    /**
     * 获取当前线程的request对应的uuid
     * @return
     */
    public static String getCurrentRequestTraceId() {
        return getRequestTraceId(WebUtil.getRequest());
    }

    /**
     * 获取当前线程的trace对象
     * @return
     */
    public static TraceInfo getTraceInfo() {
        HttpServletRequest request = WebUtil.getRequest();
        TraceInfo traceInfo = (TraceInfo) request.getAttribute(TRACE_INFO);
        if (traceInfo == null) {
            traceInfo = new TraceInfo();
            request.setAttribute(TRACE_INFO, traceInfo);
        }

        return traceInfo;
    }

    /**
     * 获取当前线程的trace对象
     * @return
     */
    public static TraceInfo getTraceInfo(HttpServletRequest request) {
        TraceInfo traceInfo = (TraceInfo) request.getAttribute(TRACE_INFO);
        if (traceInfo == null) {
            traceInfo = new TraceInfo();
            request.setAttribute(TRACE_INFO, traceInfo);
        }

        return traceInfo;
    }

    /**
     * disable ctor
     */
    private TraceUtil() {
    }
}
