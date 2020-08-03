package cn.kevin.ims.controller;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("/user/")
public class UserController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<User> saveUserInfo(@NotNull @RequestBody User sysUser) {
        User result = userService.saveUserInfo(sysUser);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<User> deleteUser(String userName) {
        User result = userService.deleteUser(userName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<User> disableUser(String userName) {
        User result = userService.disableUser(userName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<User> recoverUser(String userName) {
        User result = userService.recoverUser(userName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<User> updateUserInfo(@RequestBody User sysUser) {
        User result = userService.updateUserInfo(sysUser);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/login")
    @ResponseBody
    public Response<User> userLogin(@RequestBody User sysUser) {
        User result;
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsrName(), sysUser.getUsrPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            result = userService.userLogin(sysUser.getUsrName(), sysUser.getUsrPassword());
        } catch (UnknownAccountException e) {
            return getFailResult(404, "Message not found");
        } catch (IncorrectCredentialsException e) {
            return getFailResult(412, "Incorrect message");
        } catch (LockedAccountException e) {
            return getFailResult(401, "Account locked");
        } catch (Exception e) {
            e.printStackTrace();
            return getFailResult(408, "Unknown error");
        }
        return getSuccessResult(result);
    }
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:user/login";
    }
    @GetMapping(value = "/selectByName")
    @ResponseBody
    public Response<User> selectByName(String userName) {
        User result = userService.selectByName(userName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<User>> selectAll() {
        List<User> result = userService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<User>> selectAnyParam(@RequestBody User sysUser) {
        List<User> result = userService.selectAnyParam(sysUser);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
