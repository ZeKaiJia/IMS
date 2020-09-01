package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Role;
import cn.kevin.ims.service.PermissionService;
import cn.kevin.ims.service.RoleService;
import cn.kevin.ims.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author kevin
 */
@Api(tags = "角色模块")
@CrossOrigin
@RestController()
@RequestMapping("/role/")
public class RoleController extends BaseController {

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @ApiOperation(value = "根据用户名查询角色", notes = "根据用户名查询一条角色记录")
    @ApiImplicitParam(name = "usrName", value = "用户名", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/findRoleByUserName")
    @ResponseBody
    public Response<String> findRoleByUserName(@RequestParam String usrName) {
        Set<String> roles = roleService.findRoleByUserName(usrName);
        List<String> result = new ArrayList<String>(roles);
        if (result.size() != 0) {
            return getSuccessResult(result.get(0));
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "查询用户角色", notes = "查询所有用户角色记录")
    @GetMapping(value = "/selectAllUserRole")
    @ResponseBody
    public Response<List<String>> selectAllUserRole() {
        List<String> result = roleService.selectAllUserRole();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "查询角色", notes = "查询所有角色记录")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Role>> selectAll() {
        List<Role> result = roleService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "根据ID查询角色", notes = "根据ID查询一条角色记录")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataTypeClass = Integer.class)
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Role> selectById(@RequestParam Integer id) {
        Role result = roleService.selectById(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "更新角色", notes = "更新一条角色记录")
    @RequiresRoles("admin")
    @RequiresPermissions("user:updateRole")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Role> updateRoleInfo(@RequestBody Role role) {
        Role result = roleService.updateRoleInfo(role);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
