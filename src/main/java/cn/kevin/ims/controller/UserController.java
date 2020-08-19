package cn.kevin.ims.controller;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.implement.RoleServiceImpl;
import cn.kevin.ims.service.implement.UserServiceImpl;
import cn.kevin.ims.util.CorsUtil;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("/user/")
public class UserController extends BaseController {
    @Resource(name = "userServiceImpl")
    private UserServiceImpl userServiceImpl;
    @Resource(name = "roleServiceImpl")
    private RoleServiceImpl roleServiceImpl;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<User> saveUserInfo(@NotNull @RequestBody User sysUser, @RequestParam String usrType) {
        User result = userServiceImpl.saveUserInfo(sysUser);
        if (result != null) {
            roleServiceImpl.createUserRole(usrType, result.getUsrName());
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<User> deleteUser(@RequestParam String usrName) {
        User result = userServiceImpl.deleteUser(usrName);
        if (result != null) {
            roleServiceImpl.deleteUserRole(usrName);
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<User> disableUser(@RequestParam String usrName) {
        User result = userServiceImpl.disableUser(usrName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<User> recoverUser(@RequestParam String usrName) {
        User result = userServiceImpl.recoverUser(usrName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<User> updateUserInfo(@RequestBody User sysUser, @RequestParam String usrType) {
        User result = userServiceImpl.updateUserInfo(sysUser);
        if (result != null) {
            roleServiceImpl.changeUserRole(usrType, result.getUsrName());
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/login")
    @ResponseBody
    public Response<User> userLogin(@RequestBody User sysUser) {
        User result;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsrName(), sysUser.getUsrPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            result = userServiceImpl.userLogin(sysUser.getUsrName(), sysUser.getUsrPassword());
            session.setAttribute("user", subject);
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
    @RequiresRoles("admin")
    @RequiresPermissions("user:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<User>> selectAll() {
        List<User> result = userServiceImpl.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:selectByName")
    @GetMapping(value = "/selectByName")
    @ResponseBody
    public Response<User> selectByName(@RequestParam String usrName) {
        User result = userServiceImpl.selectByName(usrName);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:selectAnyParam")
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<User>> selectAnyParam(@RequestBody User sysUser) {
        List<User> result = userServiceImpl.selectAnyParam(sysUser);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/logout")
    @ResponseBody
    public Response<String> userLogout() {
        CorsUtil.setResponseHeader(response, request);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (SessionException e) {
            e.printStackTrace();
            return getFailResult(408, e.toString());
        }
        return getSuccessResult("logout");
    }
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:user/login";
    }
}
