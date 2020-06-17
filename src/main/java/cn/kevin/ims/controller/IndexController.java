package cn.kevin.ims.controller;

import cn.kevin.ims.model.Menu;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Kevin
 * @Date: 2020/2/18 10:30 下午
 */
@RestController()
//@Controller
@CrossOrigin
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
     * delete(in fact it's an update)
     */
    @PostMapping(value = "/redelete")
    @ResponseBody
    public Response<User> redelete(String usrId) {
        User result = userService.redelete(usrId);
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
    @ResponseBody
    public Response<User> login(HttpServletRequest request, @RequestBody User user) {
        User result;
        try {
            result = userService.login(user.getUsrId(), user.getUsrPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return getFailResult(500, "Error!");
        }
        if (result == null) {
            return getFailResult(404, "User not find!");
        }
        if (!result.getUsrPassword().equals(user.getUsrPassword())) {
            return getFailResult(412, "Password error!");
        }
        return getSuccessResult(result);
//        request.getSession().setAttribute("usrId", result.getUsrId());
//        return new ModelAndView("管理员".equals(result.getUsrType()) ? "admin" : "home")
//                .addObject("usrId", result.getUsrId())
//                .addObject("usrPassword", result.getUsrPassword())
//                .addObject("usrType", result.getUsrType())
//                .addObject("lastLogin", result.getLastLogin())
//                .addObject("utcCreate", result.getUtcCreate())
//                .addObject("utcModify", result.getUtcModify())
//                .addObject("idReal", result.getIsReal());
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

    @GetMapping(value = "/selectAdmin")
    @ResponseBody
    public Response<List<User>> selectAdmin() {
        List<User> result = userService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<List<User>> selectAdminById(String usrId) {
        List<User> result = userService.selectAdminById(usrId);
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

    /**
     * menu
     */
    @GetMapping(value = "/menus")
    @ResponseBody
    public Response<List<Menu>> showMenu() {
        List<Menu> menus = new ArrayList<>();
        menus = initMenu(menus);
        return getSuccessResult(menus);
    }

    public List<Menu> initMenu(List<Menu> menus) {
        for (int i = 0; i < 6; i++) {
            Menu menu = new Menu(i+1, "", "", null);
            menus.add(menu);
        }
        menus.get(0).setAuthName("用户管理");
        menus.get(1).setAuthName("权限管理");
        menus.get(2).setAuthName("学生管理");
        menus.get(3).setAuthName("教师管理");
        menus.get(4).setAuthName("课程管理");
        menus.get(5).setAuthName("数据统计");

        Menu menu = new Menu(11, "用户列表", "users", null);
        menus.get(0).setChildren(menu);
        menu = new Menu(21, "角色列表", "", null);
        menus.get(1).setChildren(menu);
        menu = new Menu(31, "学生列表", "", null);
        menus.get(2).setChildren(menu);
        menu = new Menu(41, "教师列表", "", null);
        menus.get(3).setChildren(menu);
        menu = new Menu(51, "课程列表", "", null);
        menus.get(4).setChildren(menu);
        menu = new Menu(61, "综合数据", "", null);
        menus.get(5).setChildren(menu);
        return menus;
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
