package cn.kevin.ims.controller;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.util.SerialUtil;
import cn.kevin.ims.vo.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type User controller.
 * UserController
 * @Author: Kevin
 * @Date: 2020 /2/18 10:30 下午
 */
@RestController()
@CrossOrigin
@RequestMapping("/login/")
public class UserController extends BaseController {
    /**
     * The constant INDEX.
     */
    private static final String INDEX = "index";

    /**
     * The User service.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Hello world string.
     * 测试方法
     * @param name the name
     * @return the string
     */
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    /**
     * insert
     * 插入
     * @param user the user
     * @return the response
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
     * 删除
     * @param usrId the usr id
     * @return the response
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Response<User> save(String usrId) {
        User result = userService.save(usrId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     * 禁用
     * @param usrId the usr id
     * @return the response
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
     * 恢复禁用
     * @param usrId the usr id
     * @return the response
     */
    @PostMapping(value = "/reDelete")
    @ResponseBody
    public Response<User> reDelete(String usrId) {
        User result = userService.reDelete(usrId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * update
     * 更新
     * @param user the user
     * @return the response
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

    /**
     * Login response.
     * 登录
     * @param request the request
     * @param user    the user
     * @return the response
     */
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
    }

    /**
     * To login string.
     * 返回登录
     * @return the string
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * Home string.
     * 管理员页面
     * @return the string
     */
    @GetMapping(value = "home")
    public String home() {
        return "Admin";
    }

    /**
     * select
     * 查找单个非禁用数据
     * @param usrId the usr id
     * @return the response
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

    /**
     * Select all response.
     * 查找全部非禁用数据
     * @return the response
     */
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<User>> selectAll() {
        List<User> result = userService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin response.
     * 查找全部数据
     * @return the response
     */
    @GetMapping(value = "/selectAdmin")
    @ResponseBody
    public Response<List<User>> selectAdmin() {
        List<User> result = userService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin by id response.
     * 查找单个数据
     * @param usrId the usr id
     * @return the response
     */
    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<User> selectAdminById(String usrId) {
        User result = userService.selectAdminById(usrId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select by all info response.
     * 按任意字段查找非禁用数据
     * @param user the user
     * @return the response
     */
    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<User>> selectByAllInfo(@RequestBody User user) {
        List<User> result = userService.selectByAllInfo(user);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }
}
