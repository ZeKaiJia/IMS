package cn.kevin.ims.shiro;

import cn.kevin.ims.util.CorsUtil;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Kevin
 * @Date: 2020/8/3 8:36 下午
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "shiroCrossFilter")
public class ShiroLoginFilter  implements Filter {

    private FilterConfig config = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {
        this.config = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        CorsUtil.setResponseHeader(response, request);
        filterChain.doFilter( servletRequest, response );
    }
}
