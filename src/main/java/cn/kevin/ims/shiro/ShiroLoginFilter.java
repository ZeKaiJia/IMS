package cn.kevin.ims.shiro;

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
        // 允许哪些Origin发起跨域请求,nginx下正常
        // TODO : 部署时修改为本地域名
        response.setHeader( "Access-Control-Allow-Origin", "http://localhost:9999" );
        // 允许请求的方法
        response.setHeader( "Access-Control-Allow-Methods", "OPTIONS, GET, POST" );
        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
        response.setHeader( "Access-Control-Max-Age", "3600" );
        // 表明它允许跨域请求包含xxx头
        response.setHeader( "Access-Control-Allow-Headers", "content-type" );
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        if ("OPTIONS".equals(request.getMethod().toUpperCase())) {
            response.setStatus( 200 );
            return;
        }
        filterChain.doFilter( servletRequest, response );
    }


}
