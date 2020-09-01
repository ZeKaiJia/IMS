package cn.kevin.ims.controller;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.RoleService;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.util.CorsUtil;
import cn.kevin.ims.vo.Response;
import io.swagger.annotations.*;
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

/**
 * @author kevin
 */
@Api(tags = "用户模块")
@CrossOrigin
@RestController()
@RequestMapping("/user/")
public class UserController extends BaseController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @ApiOperation(value = "默认Get请求", notes = "请求不存在的路径时调用此默认接口")
    @ApiImplicitParam(name = "name", value = "默认字段", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    @ApiOperation(value = "添加用户", notes = "插入一条用户记录")
    @ApiImplicitParam(name = "usrType", value = "用户角色", required = true, dataTypeClass = String.class)
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

    @ApiOperation(value = "删除用户", notes = "删除一条用户记录")
    @ApiImplicitParam(name = "usrName", value = "用户名", required = true, dataTypeClass = String.class)
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

    @ApiOperation(value = "锁定用户", notes = "锁定一条用户记录")
    @ApiImplicitParam(name = "usrName", value = "用户名", required = true, dataTypeClass = String.class)
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

    @ApiOperation(value = "恢复用户", notes = "恢复一条用户记录")
    @ApiImplicitParam(name = "usrName", value = "用户名", required = true, dataTypeClass = String.class)
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

    @ApiOperation(value = "更新用户", notes = "更新一条用户记录")
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

    @ApiOperation(value = "用户登录", notes = "登录系统")
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

    @ApiOperation(value = "查询用户列表", notes = "查询所有用户记录")
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

    @ApiOperation(value = "按用户名查询用户", notes = "按用户名查询一条用户记录")
    @ApiImplicitParam(name = "usrName", value = "用户名", required = true, dataTypeClass = String.class)
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

    @ApiOperation(value = "按任意字段查询用户", notes = "按任意字段查询一条或多条用户记录")
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

    @ApiOperation(value = "用户退出", notes = "退出系统")
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

    @ApiOperation(value = "强制", notes = "后台未登录用户强制登录")
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:user/login";
    }
}
