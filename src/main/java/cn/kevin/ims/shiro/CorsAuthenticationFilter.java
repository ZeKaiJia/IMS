package cn.kevin.ims.shiro;

import cn.kevin.ims.util.CorsUtil;
import cn.kevin.ims.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kevin
 * @Date: 2020/8/4 2:06 下午
 */
public class CorsAuthenticationFilter extends UserFilter {

    private static final Logger logger = LoggerFactory.getLogger(CorsAuthenticationFilter.class);

    public CorsAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if ("OPTIONS".equals(((HttpServletRequest) request).getMethod().toUpperCase())) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        CorsUtil.setResponseHeader(res, req);
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map= new HashMap<>(16);
        map.put("success", false);
        map.put("code", 702);
        map.put("message", "用户未登录");
        map.put("timestamp", DateUtil.currentSecond());
        writer.write(JSON.toJSONString(map));
        writer.close();
        return false;
    }
}
