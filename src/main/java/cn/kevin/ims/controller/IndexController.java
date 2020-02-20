package cn.kevin.ims.controller;

import cn.kevin.ims.aop.LoginAction;
import cn.kevin.ims.entity.Student;
import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.util.DateUtils;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * @Author: Kevin
 * @Date: 2020/2/18 10:30 下午
 */
@RestController()
@RequestMapping("/login/")
public class IndexController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name)  {
        return "Hello " + name;
    }

    @RequestMapping(value = {"", "index", "login"}, method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    /** insert */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Response<User> insert(@NotNull @RequestBody User user)  {
        User result = userService.insert(user);
        if (result!=null) {
            return getSuccessResult(result);
        }
        return getFailResult(405,"ID already exist!");
    }

    /** save(in fact it's delete) */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Response<List<User>> save()  {
        List<User> result = userService.save();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404,"Message not find!");
    }

    /** delete(in fact it's an update) */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Response<User> delete(String usrId)  {
        User result = userService.delete(usrId);
        if (result!=null) {
            return getSuccessResult(result);
        }
        return getFailResult(404,"ID not find!");
    }

    /** update */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Response<User> update(@RequestBody User user)  {
        User result = userService.update(user);
        if (result!=null) {
            return  getSuccessResult(result);
        }
        return getFailResult(404,"ID not find!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, @RequestBody User user) {
        User result;
        try {
            result = userService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("login")
                    .addObject("msg", "Try again");
        }
        request.getSession().setAttribute("usrId", result.getUsrId());
        return new ModelAndView("管理员".equals(result.getUsrType()) ? "admin" : "home")
                .addObject("usrId", result.getUsrId())
                .addObject("usrPassword", result.getUsrPassword())
                .addObject("usrType", result.getUsrType())
                .addObject("lastLogin", result.getLastLogin())
                .addObject("utcCreate", result.getUtcCreate())
                .addObject("utcModify", result.getUtcModify())
                .addObject("idReal", result.getIsReal());
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "Admin";
    }

    /** select */
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    @ResponseBody
    public Response<User> select(String usrId)  {
        User result = userService.select(usrId);
        if (result!=null) {
            return getSuccessResult(result);
        }
        return getFailResult(404,"ID not find!");
    }
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<User>> selectAll()  {
        List<User> result = userService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404,"Message not find!");
    }
    @RequestMapping(value = "/selectByAllInfo",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<User>> selectByAllInfo(@RequestBody User user)  {
        List<User> result = userService.selectByAllInfo(user);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404,"Message not find!");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsrId("12345");
        user.setUsrPassword("12345");
        user.setUsrType("管理员");
        user.setLastLogin(DateUtils.currentSecond());
        System.out.println(JSON.toJSONString(user));
    }
}