package cn.kevin.ims.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Login action.
 *
 * @Author: Kevin
 * @Date: 2020 /2/18 7:07 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAction {
    /**
     * Value string.
     * 空字段
     * @return the string
     */
    String value() default "";
}
