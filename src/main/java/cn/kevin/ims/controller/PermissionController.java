package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Permission;
import cn.kevin.ims.service.PermissionService;
import cn.kevin.ims.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author kevin
 */
@Api(tags = "权限模块")
@CrossOrigin
@RestController()
@RequestMapping("/permission/")
public class PermissionController extends BaseController {

    @Resource(name = "permissionServiceImpl")
    private PermissionService permissionService;

    @ApiOperation(value = "查询权限", notes = "查询所有权限记录")
    @GetMapping(value = "/selectAllPermission")
    @ResponseBody
    public Response<List<Permission>> selectAllPermission() {
        List<Permission> result = permissionService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "更新权限", notes = "更新一条权限记录")
    @RequiresRoles("admin")
    @RequiresPermissions("user:updatePermission")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Permission> updatePermissionInfo(@RequestBody Permission permission) {
        Permission result = permissionService.updatePermissionInfo(permission);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "根据名称查询权限", notes = "根据名称查询一条权限记录")
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Permission> selectByName(@RequestParam Integer id) {
        Permission result = permissionService.selectById(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "创建角色权限链接", notes = "创建多条新的角色与权限链接记录")
    @ApiImplicitParam(name = "permission", value = "权限名", required = true, dataTypeClass = String.class)
    @RequiresPermissions("user:managePermission")
    @RequiresRoles("admin")
    @PostMapping(value = "/managePermission")
    @ResponseBody
    public Response<String> insertNewPermission(@RequestParam String role, @RequestParam String permission){
        String result = permissionService.insertNewPermission(role, permission);
        if ("Success!".equals(result)) {
            return getSuccessResult(result);
        }
        return getFailResult(500, "Error!");
    }

    @ApiOperation(value = "删除角色权限", notes = "删除某角色的所有权限记录")
    @ApiImplicitParam(name = "role", value = "角色名", required = true, dataTypeClass = String.class)
    @RequiresPermissions("user:preManagePermission")
    @RequiresRoles("admin")
    @PostMapping(value = "/preManagePermission")
    @ResponseBody
    public Response<String> deleteRolePermission(@RequestParam String role){
        String result = permissionService.deleteRolePermission(role);
        if ("Success!".equals(result)) {
            return getSuccessResult(result);
        }
        return getFailResult(500, "Error!");
    }

    @ApiOperation(value = "根据角色查询权限", notes = "根据角色查询所有权限")
    @ApiImplicitParam(name = "role", value = "角色名", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/findPermissionByRole")
    @ResponseBody
    public Response<Set<String>> findPermissionByRole(@RequestParam String role) {
        Set<String> results = permissionService.findPermissionByRole(role);
        if (results.size() != 0) {
            return getSuccessResult(results);
        }
        return getFailResult(404, "Message not found!");
    }
}
