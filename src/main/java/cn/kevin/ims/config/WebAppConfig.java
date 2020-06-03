package cn.kevin.ims.config;

import cn.kevin.ims.handler.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Kevin
 * @Date: 2020/6/3 10:31 下午
 */
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("api/path/**").excludePathPatterns("api/path/login");
    }
}
