package cn.kevin.ims.controller;

import cn.kevin.ims.util.DateUtils;
import cn.kevin.ims.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author kevin
 */
public abstract class BaseController {
    void doBiz(int choice) {
    }

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

//    @Autowired
//    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;


    protected <T> Response<T> getFailResult(Integer code, String msg) {
        return new Response<T>()
                .setCode(code)
                .setMessage(msg)
                .setSuccess(false)
                .setData(null)
                .setTimestamp(DateUtils.currentSecond());
    }


    protected <T> Response<T> getSuccessResult(T data) {
        return new Response<T>()
                .setCode(200)
                .setMessage("Success!")
                .setSuccess(true)
                .setData(data)
                .setTimestamp(DateUtils.currentSecond());
    }

    protected long getCurrentUserId() {
        return 1L;
    }

    protected boolean checkIllegalityScore(Integer number) {
        return number < 0 || number > 100;
    }

    protected boolean checkLegalityId(Integer number) {
        return number >= 0;
    }

    protected boolean checkLegalityEmail(String string) {
        return string.contains("@");
    }

}
