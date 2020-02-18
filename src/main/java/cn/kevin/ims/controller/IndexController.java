package cn.kevin.ims.controller;

import cn.kevin.ims.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Kevin
 * @Date: 2020/2/18 10:30 下午
 */
@RestController()
@RequestMapping("/login/")
public class IndexController extends BaseController {
    private static Logger logger = LogManager.getLogger(IndexController.class.getName());

    @Resource
    private UserService userService;
}
