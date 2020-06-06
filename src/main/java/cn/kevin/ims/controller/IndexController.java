package cn.kevin.ims.controller;

import cn.kevin.ims.model.User;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.util.DateUtil;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Kevin
 * @Date: 2020/2/18 10:30 下午
 */
//@RestController()
@Controller
@RequestMapping("/login/")
public class IndexController extends BaseController {
    private static final String INDEX = "index";

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

//    @GetMapping(value = {"", "/index", "/login"})
//    public String getIndex() {
//        return INDEX;
//    }

    /**
     * insert
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<User> insert(@NotNull @RequestBody User user) {
        User result = userService.insert(user);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "ID already exist!");
    }

    /**
     * save(in fact it's delete)
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Response<List<User>> save() {
        List<User> result = userService.save();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<User> delete(String usrId) {
        User result = userService.delete(usrId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * update
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<User> update(@RequestBody User user) {
        User result = userService.update(user);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    @PostMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, String usrId, String usrPassword) {
        User result;
        try {
            result = userService.login(usrId, usrPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("login")
                    .addObject("msg", "出错啦，请再试一次");
        }
        if (result == null) {
            return new ModelAndView("login")
                    .addObject("msg", "用户ID不存在，请点击注册");
        }
        if (!result.getUsrPassword().equals(usrPassword)) {
            return new ModelAndView("login")
                    .addObject("msg", "密码错误，请重新填写");
        }
//        request.getSession().setAttribute("usrId", result.getUsrId());
        return new ModelAndView("管理员".equals(result.getUsrType()) ? "admin" : "home")
                .addObject("usrId", result.getUsrId())
                .addObject("usrPassword", result.getUsrPassword())
                .addObject("usrType", result.getUsrType())
                .addObject("lastLogin", result.getLastLogin())
                .addObject("utcCreate", result.getUtcCreate())
                .addObject("utcModify", result.getUtcModify())
                .addObject("idReal", result.getIsReal());
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @GetMapping(value = "home")
    public String home() {
        return "Admin";
    }

    /**
     * select
     */
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<User> select(String usrId) {
        User result = userService.select(usrId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<User>> selectAll() {
        List<User> result = userService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<User>> selectByAllInfo(@RequestBody User user) {
        List<User> result = userService.selectByAllInfo(user);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsrId("12345");
        user.setUsrPassword("12345");
        user.setUsrType("管理员");
        user.setLastLogin(DateUtil.currentSecond());
        System.out.println(JSON.toJSONString(user));
    }
}
