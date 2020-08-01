package cn.kevin.ims.controller;

import cn.kevin.ims.util.DateUtil;
import cn.kevin.ims.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Base controller.
 * BaseController
 * @author kevin
 */
public abstract class BaseController {
    /**
     * Do biz.
     * 事物
     * @param choice the choice
     */
    void doBiz(int choice) {
    }

    /**
     * The Logger.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The Request.
     * 请求
     */
    @Autowired
    protected HttpServletRequest request;

    /**
     * The Response.
     * 响应
     */
    @Autowired(required = false)
    protected HttpServletResponse response;

    /**
     * The Session.
     * 会话
     */
    @Autowired
    protected HttpSession session;


    /**
     * 获取失败信息
     * @method: get fail result.
     * @param: <T> the type parameter
     * @param: code the code
     * @param: msg the msg
     * @return: fail result
     */
    protected <T> Response<T> getFailResult(Integer code, String msg) {
        return new Response<T>()
                .setCode(code)
                .setMessage(msg)
                .setSuccess(false)
                .setData(null)
                .setTimestamp(DateUtil.currentSecond());
    }


    /**
     * 获取成功信息
     * @method: get success result.
     * @param: <T> the type parameter
     * @param: data the data
     * @return: success result
     */
    protected <T> Response<T> getSuccessResult(T data) {
        return new Response<T>()
                .setCode(200)
                .setMessage("Success!")
                .setSuccess(true)
                .setData(data)
                .setTimestamp(DateUtil.currentSecond());
    }

    /**
     * 获取ID
     * @method: get current user id.
     * @return: current user id
     */
    protected long getCurrentUserId() {
        return 1L;
    }

    /**
     * Check illegality score boolean.
     * 检查分数（以下三个功能均在前端实现）
     * @param number the number
     * @return the boolean
     */
    protected boolean checkIllegalityScore(Integer number) {
        return number < 0 || number > 100;
    }

    /**
     * Check legality id boolean.
     * 检查ID
     * @param number the number
     * @return the boolean
     */
    protected boolean checkLegalityId(Integer number) {
        return number >= 0;
    }

    /**
     * Check legality email boolean.
     * 检查Email
     * @param string the string
     * @return the boolean
     */
    protected boolean checkLegalityEmail(String string) {
        return string.contains("@");
    }

}
