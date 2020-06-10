package com.zilinsproject.mybatis.interceptor;

import com.zilinsproject.mybatis.utils.NetworkConfigConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置拦截器打印日志
 * @author zilinsmac
 */

@Slf4j
@Component
public class RequestIdTraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String traceId = UUID.randomUUID().toString().replace("-", "");
        MDC.put(NetworkConfigConst.TRACE_ID, traceId);
        log.info("生成traceId: " + traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
