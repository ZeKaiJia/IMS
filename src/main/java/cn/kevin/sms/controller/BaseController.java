package cn.kevin.sms.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 */
public abstract class BaseController {
    abstract void doBiz(int choice);



    protected Object getSuccessResult(Object data) {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("messge", "成功");
        retMap.put("data", data);
        retMap.put("code", 200);
        return retMap;
    }

}
