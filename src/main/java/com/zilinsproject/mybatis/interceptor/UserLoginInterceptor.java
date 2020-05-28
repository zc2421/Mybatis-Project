package com.zilinsproject.mybatis.interceptor;

import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.utils.CustomerConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zilinsmac
 */

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer user_id = (Integer) request.getSession().getAttribute(CustomerConst.CURRENT_USER);
        if (user_id == null){
            response.getWriter().print("ERROR: USER_NOT_LOGIN");
            return false;
        }
        return true;
    }
}
