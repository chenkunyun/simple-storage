package com.kchen.storage.common.util.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 与web相关的帮助函数
 */
public final class WebUtil {

    /**
     * 这里写一个bean来标识log是因为我想把url字段输出在前面，用JSONObject.put方法输出的时候会排序
     */
    private static class RequestLog {
        @JSONField(ordinal = 0)
        private String url;
        @JSONField(ordinal = 1)
        private Map<String, String> params;

        public RequestLog() {
        }

        public RequestLog(String url, Map<String, String> params) {
            this.url = url;
            this.params = params;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(Map<String, String> params) {
            this.params = params;
        }
    }

    /**
     * 获取当前线程对应的http请求对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前线程的request请求参数
     * @return
     */
    public static Map<String, String> getCurrentParameterMap() {
        return getParameterMap(getRequest());
    }

    /**
     * 获取参数的map
     *
     * @param request the request
     * @return the parameter map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> parameterMap = new HashMap<String, String>();
        Set<String> keys = request.getParameterMap().keySet();
        for (String key : keys) {
            if (StringUtils.isEmpty(request.getParameter(key))) {
                continue;
            }
            parameterMap.put(key, request.getParameter(key));
        }
        return parameterMap;
    }

    /**
     * 获取请求相关的log信息，用于记录到log
     * 目前包括的信息如下：
     *  1. 请求路径
     *  2. 请求参数
     * @return
     */
    public static String getRequestLogString(HttpServletRequest request) {
        RequestLog requestLog = new RequestLog();
        requestLog.setUrl(request.getRequestURL().toString());
        requestLog.setParams(getCurrentParameterMap());
        return JSONObject.toJSONString(requestLog);
    }

    /**
     * 获取请求的base path
     * scheme://serverName:port/
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        return basePath;
    }

    /**
     * disable ctor
     */
    private WebUtil() {

    }
}