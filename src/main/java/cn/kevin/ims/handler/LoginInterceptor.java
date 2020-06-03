package cn.kevin.ims.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Kevin
 * @Date: 2020/6/3 10:18 下午
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //判断当前请求地址是否是登录/注册地址
        if (! (uri.contains("login") || uri.contains("Login") || uri.contains("register"))) {
            //非登录请求
            if (request.getSession().getAttribute("user") != null) {
                return true;
            } else {
                if (uri.contains("css") || uri.contains("js") || uri.contains("images")) {
                    return true;
                } else {
                    response.sendRedirect(request.getContextPath() + "/login/toLogin");
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
