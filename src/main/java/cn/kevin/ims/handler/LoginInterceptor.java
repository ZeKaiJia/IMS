package cn.kevin.ims.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Login interceptor.
 * LoginInterceptor
 * 登录拦截
 * @Author: Kevin
 * @Date: 2020 /6/3 10:18 下午
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * The constant LOGIN.
     */
    public static String LOGIN = "login";
    /**
     * The constant REGISTER.
     */
    public static String REGISTER = "register";
    /**
     * The constant USER.
     */
    public static String USER = "user";
    /**
     * The constant CSS.
     */
    public static String CSS = "css";
    /**
     * The constant JS.
     */
    public static String JS = "js";
    /**
     * The constant IMAGES.
     */
    public static String IMAGES = "images";

    /**
     * Pre handle boolean.
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     * @return the boolean
     * @throws Exception the exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (! (uri.contains(LOGIN) || uri.contains(REGISTER))) {
            if (request.getSession().getAttribute(USER) != null) {
                return true;
            } else {
                if (uri.contains(CSS) || uri.contains(JS) || uri.contains(IMAGES)) {
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
