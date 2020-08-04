package cn.kevin.ims.handler;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * exception class
 * 全局异常类
 * @Author: Kevin
 * @Date: 2020/8/3 6:55 下午
 */
@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(ShiroException.class)
    public String doHandleShiroException(ShiroException se, Model model) {
        se.printStackTrace();
        if(se instanceof UnknownAccountException) {
            return "该账户不存在";
        } else if (se instanceof LockedAccountException) {
            return "该账户已锁定";
        } else if (se instanceof IncorrectCredentialsException) {
            return "密码错误请重试";
        } else if (se instanceof AuthorizationException) {
            return "没有相应权限";
        } else {
            return "操作失败请重试";
        }
    }
}
