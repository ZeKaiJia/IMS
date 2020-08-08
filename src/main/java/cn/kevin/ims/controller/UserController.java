package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Permission;
import cn.kevin.ims.entity.Role;
import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.PermissionService;
import cn.kevin.ims.service.RoleService;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController()
@CrossOrigin
@RequestMapping("/user/")
public class UserController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;
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
        User result = userService.saveUserInfo(sysUser);
        if (result != null) {
            roleService.createUserRole(usrType, result.getUsrName());
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<User> deleteUser(@RequestParam String usrName) {
        User result = userService.deleteUser(usrName);
        if (result != null) {
            roleService.deleteUserRole(usrName);
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<User> disableUser(@RequestParam String usrName) {
        User result = userService.disableUser(usrName);
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
        User result = userService.recoverUser(usrName);
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
        User result = userService.updateUserInfo(sysUser);
        if (result != null) {
            roleService.changeUserRole(usrType, result.getUsrName());
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
            result = userService.userLogin(sysUser.getUsrName(), sysUser.getUsrPassword());
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
        List<User> result = userService.selectAll();
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
        User result = userService.selectByName(usrName);
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
        List<User> result = userService.selectAnyParam(sysUser);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/logout")
    @ResponseBody
    public Response<String> userLogout() {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9999");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return getSuccessResult("logout");
    }
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:user/login";
    }
}
