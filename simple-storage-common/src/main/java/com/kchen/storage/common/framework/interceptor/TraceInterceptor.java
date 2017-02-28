package com.kchen.storage.common.framework.interceptor;


import com.kchen.storage.common.util.web.TraceUtil;
import com.kchen.storage.common.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ky.chen on 2016/8/26.
 * 请求跟踪相关
 */
public class TraceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);

    //记录请求开始处理的时间
    private ThreadLocal<Long> requestBeginTime = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //每个请求分配一个uuid，并记录请求开始处理的时间
        String traceId = TraceUtil.assignRequestTraceId(request);
        logger.info("request begin, traceid:[{}], request param:{}", traceId, WebUtil.getRequestLogString(request));
        requestBeginTime.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        String traceId = TraceUtil.getRequestTraceId(request);
        long requestElapseTime = System.currentTimeMillis() - requestBeginTime.get();
        if (requestElapseTime > 3*1000) {
            //请求大于3秒
            logger.error("request end, traceid:[{}], time used:{} ms", traceId, requestElapseTime);
        } else {
            logger.info("request end, traceid:[{}], time used:{} ms", traceId, requestElapseTime);
        }
    }
}