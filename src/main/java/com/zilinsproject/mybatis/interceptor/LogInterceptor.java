package com.zilinsproject.mybatis.interceptor;

import com.zilinsproject.mybatis.utils.NetworkConfigConst;
import com.zilinsproject.mybatis.utils.NetworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 日志拦截器
 * @author zilinsmac
 */

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        JSONObject logJsonMap = new JSONObject();
        //start time
        Long startTime = System.currentTimeMillis();
        request.setAttribute(NetworkConfigConst.START_TIME, startTime);
        //ip
        logJsonMap.put(NetworkConfigConst.IP, NetworkUtils.getClientIpAddress(request));
        //path
        logJsonMap.put(NetworkConfigConst.REQ_PATH, request.getRequestURI());
        //parameter
        Map<String, String[]> map = request.getParameterMap();
        map.forEach(logJsonMap::put);
        //traceId
        logJsonMap.put(NetworkConfigConst.TRACE_ID, MDC.get(NetworkConfigConst.TRACE_ID));
        //other
        logJsonMap.put(NetworkConfigConst.LOG_TYPE, NetworkConfigConst.START);

        log.info(NetworkConfigConst.LOG_PREFIX + logJsonMap.toJSONString());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        JSONObject logJsonMap = new JSONObject();
        Long startTime = (Long) request.getAttribute(NetworkConfigConst.START_TIME);
        //duration
        logJsonMap.put(NetworkConfigConst.CONSUME_TIME, System.currentTimeMillis() - startTime);
        //traceId
        logJsonMap.put(NetworkConfigConst.TRACE_ID, MDC.get(NetworkConfigConst.TRACE_ID));
        //response body
        logJsonMap.put(NetworkConfigConst.RES_BODY, request.getAttribute(NetworkConfigConst.RES_BODY));
        //other
        logJsonMap.put(NetworkConfigConst.LOG_TYPE, NetworkConfigConst.END);

        log.info(NetworkConfigConst.LOG_PREFIX + logJsonMap.toJSONString());
    }



}
