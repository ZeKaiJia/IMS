package cn.kevin.ims.config;

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
public class CORSAuthenticationFilter extends UserFilter {

    private static final Logger logger = LoggerFactory.getLogger(CORSAuthenticationFilter.class);

    public CORSAuthenticationFilter() {
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
        // TODO : 部署时修改为本地域名
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:9999");
        res.setHeader("Access-Control-Allow-Headers", "content-type");
        res.setHeader("Access-Control-Allow-Credentials", "true");
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
