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
 * The type Login aspect.
 *
 * @Author: Kevin
 * @Date: 2020 /2/18 7:09 下午
 */
@Component
@Aspect
public class LoginAspect {
    /**
     * The constant logger.
     */
    private static Logger logger = LogManager.getLogger(LoginAspect.class.getName());
    /**
     * The constant NEED_LOGIN_OUT.
     */
    private static final String NEED_LOGIN_OUT = "false";
    /**
     * The Need login in.
     */
    static final String NEED_LOGIN_IN = "true";

    /**
     * Login action.
     */
    @Pointcut("@annotation(cn.kevin.ims.aop.LoginAction)")
    public void loginAction() {
    }

    /**
     * To login object.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @return the object
     * @throws Throwable the throwable
     */
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
        if (NEED_LOGIN_IN.equals(annotation.value())) {
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
