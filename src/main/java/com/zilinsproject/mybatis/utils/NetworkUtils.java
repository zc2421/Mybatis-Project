package com.zilinsproject.mybatis.utils;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;


/**
 * NETWORK统计
 * @author zilinsmac
 */
public class NetworkUtils {
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getClientIpAddress(HttpServletRequest request) {
        String rip = request.getRemoteAddr();
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                rip = ip;
                break;
            }
        }
        int pos = rip.indexOf(',');
        if (pos >= 0) {
            rip = rip.substring(0, pos);
        }
        return rip;
    }









}
