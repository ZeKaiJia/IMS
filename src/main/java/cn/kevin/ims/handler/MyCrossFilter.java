package cn.kevin.ims.handler;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type My cross filter.
 * MyCrossFilter
 * 跨域处理器
 * @Author: Kevin
 * @Date: 2020 /6/10 3:17 下午
 */
@Component
public class MyCrossFilter implements Filter {
    /**
     * Destroy.
     */
    @Override
    public void destroy() {
    }

    /**
     * Do filter.
     *
     * @param req   the req
     * @param res   the res
     * @param chain the chain
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        chain.doFilter(req, res);

    }

    /**
     * Init.
     *
     * @param arg0 the arg 0
     * @throws ServletException the servlet exception
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
