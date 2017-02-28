package com.kchen.storage.common.framework.controller;

import com.alibaba.fastjson.JSONObject;
import com.kchen.storage.common.util.web.TraceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;


public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * The Enum ALERTTYPE.
	 */
	private enum ALERTTYPE {

		/** The error. */
		ERROR,
		/** The info. */
		INFO
	}


	/**
	 * Gets the real ip addr.
	 *
	 * @return the real ip addr
	 */
	protected String getRealIpAddr() {
		HttpServletRequest req = this.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		if (StringUtils.contains(ip, ",")) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}

	/**
	 * Sets the alert message.
	 *
	 * @param message
	 *            the message
	 * @param type
	 *            the type
	 */
	protected void setAlertMessage(String message, ALERTTYPE type) {
		this.getRequest().setAttribute("alertMessage", message);
		this.getRequest().setAttribute("alertType", type);
	}

	/**
	 * Error.
	 *
	 * @param message
	 *            the message
	 * @return the string
	 */
	public String error(String message) {
		this.setAlertMessage(message, ALERTTYPE.ERROR);
		return "error";
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	/**
	 * Response_404.
	 *
	 * @param response
	 *            the response
	 * @return the string
	 */
	protected String response_404(HttpServletResponse response) {
		try {
			response.sendError(404);
		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
		}
		return null;// 设置responseCode为404后,不需要显示返回页面
	}

	/**
	 * Sets the request attribute.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	protected void setRequestAttribute(String key, Object value) {
		this.getRequest().setAttribute(key, value);
	}

	/**
	 * Gets the parameter map.
	 *
	 * @return the parameter map
	 */
	protected Map<String, Object> getParameterMap() {
		return getParameterMap(this.getRequest());
	}

	/**
	 * Gets the parameter map.
	 *
	 * @param request
	 *            the request
	 * @return the parameter map
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
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
	 * 拦截@Valid注解验证异常
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the object
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
		String traceId = TraceUtil.getRequestTraceId(request);
		logger.error("request validation exception: traceid:" + traceId, ex);
		if (isAjaxRequest(request)) {
			JSONObject result = new JSONObject();
			result.put("uuid", traceId);
			result.put("msg", ex.getMessage());
			result.put("data", "error");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-type", "application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			flush(response, result);
			return null;
		} else {
			ModelAndView exceptionView = new ModelAndView("500");
			exceptionView.addObject("uuid", traceId);
			return exceptionView;
		}
	}

	/**
	 * 拦截所有系统级异常.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the object
	 */
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		String traceId = TraceUtil.getRequestTraceId(request);
		logger.error("request exception: traceid:" + traceId, ex);
		if (isAjaxRequest(request)) {
			JSONObject result = new JSONObject();
			result.put("uuid", traceId);
			result.put("msg", ex.getMessage());
			result.put("data", null);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-type", "application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			flush(response, result);
			return null;
		} else {
			ModelAndView exceptionView = new ModelAndView("500");
			exceptionView.addObject("uuid", traceId);
			return exceptionView;
		}
	}

	/**
	 * Checks if is ajax request.
	 *
	 * @param request
	 *            the request
	 * @return true, if is ajax request
	 */
	protected boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (StringUtils.isNotBlank(requestType) && StringUtils.equals(requestType, "XMLHttpRequest")) {
			return true;
		}
		return false;
	}

	/**
	 * Flush.
	 *
	 * @param response
	 *            the response
	 * @param data
	 *            the data
	 */
	protected void flush(HttpServletResponse response, Object data) {
		try {
			BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(JSONObject.toJSONString(data).getBytes("UTF-8"));
			os.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds the root cookie.
	 *
	 * @param key
	 *            the key
	 * @param val
	 *            the val
	 * @param response
	 *            the response
	 */
	protected void addRootCookie(String key, String val, HttpServletResponse response) {
		Cookie cookie = WebUtils.getCookie(getRequest(), key);

		if (cookie == null)
			cookie = new Cookie(key, val);
		else
			cookie.setValue(val);

		cookie.setPath("/");
		response.addCookie(cookie);
		cookie.setMaxAge(1200);
	}

	/**
	 * 获取session
	 * @return
     */
	protected HttpSession getSession() {
		return this.getRequest().getSession();
	}

	/**
	 * 获取请求头的map
	 * @return
     */
	protected Map<String, String> getHeaderMap() {
		return getHeaderMap(this.getRequest());
	}

	protected Map<String, String> getHeaderMap(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		Enumeration headNames = request.getHeaderNames();
		while(headNames.hasMoreElements()){
			String name = (String) headNames.nextElement();
			result.put(name, request.getHeader(name));
		}

		return result;
	}

	/**
	 * 获取locale
	 * @return
	 */
	protected Locale getLocale() {
		return getRequest().getLocale();
	}
}