package cn.kevin.sms.controller;

import cn.kevin.sms.util.DateUtils;
import cn.kevin.sms.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 */
public abstract class BaseController {
    void doBiz(int choice) {
    }

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;


    protected <T> Response<T> getFailResult(String code, String msg) {
        return new Response<T>()
                .setCode(520)
                .setMessage(msg)
                .setSuccess(false)
                .setData(null)
                .setTimestamp(DateUtils.currentSecond());
    }


    protected <T> Response<T> getSuccessResult(T data) {
        return new Response<T>()
                .setCode(200)
                .setMessage("success")
                .setSuccess(true)
                .setData(data)
                .setTimestamp(DateUtils.currentSecond());
    }

    protected long getCurrentUserId() {
        return 1L;
    }
}
