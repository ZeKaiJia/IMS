package cn.kevin.ims.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Kevin
 * @Date: 2020/2/18 7:09 下午
 */
@Component
@Aspect
public class LoginAspect {
    private static Logger logger = LogManager.getLogger(LoginAspect.class.getName());
    private static final String NEED_LOGIN_OUT = "false";
    static final String NEED_LOGIN_IN = "true";

    @Pointcut("@annotation(cn.kevin.ims.aop.LoginAction)")
    public void loginAction(){}

    @Around("loginAction()")
    public Object toLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取request和response
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String requestUri = request.getRequestURI();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LoginAction annotation = signature.getMethod().getAnnotation(LoginAction.class);
        if (NEED_LOGIN_IN.equals(annotation.value())){
            assert response != null;
            response.sendRedirect("/index.html");
            System.out.println("Login needed");
            return null;
        } else {
            Object object = proceedingJoinPoint.proceed();
            System.out.println("接口访问成功");
            return object;
        }
    }
}
