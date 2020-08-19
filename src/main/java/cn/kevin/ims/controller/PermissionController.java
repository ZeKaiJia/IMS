package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Permission;
import cn.kevin.ims.service.implement.PermissionServiceImpl;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/permission/")
public class PermissionController extends BaseController {
    @Resource
    private PermissionServiceImpl permissionServiceImpl;
    @GetMapping(value = "/selectAllPermission")
    @ResponseBody
    public Response<List<Permission>> selectAllPermission() {
        List<Permission> result = permissionServiceImpl.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresRoles("admin")
    @RequiresPermissions("user:updatePermission")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Permission> updatePermissionInfo(@RequestBody Permission permission) {
        Permission result = permissionServiceImpl.updatePermissionInfo(permission);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Permission> selectByName(@RequestParam Integer id) {
        Permission result = permissionServiceImpl.selectById(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("user:managePermission")
    @RequiresRoles("admin")
    @PostMapping(value = "/managePermission")
    @ResponseBody
    public Response<String> insertNewPermission(@RequestParam String role, @RequestParam String permission){
        String result = permissionServiceImpl.insertNewPermission(role, permission);
        if ("Success!".equals(result)) {
            return getSuccessResult(result);
        }
        return getFailResult(500, "Error!");
    }
    @RequiresPermissions("user:preManagePermission")
    @RequiresRoles("admin")
    @PostMapping(value = "/preManagePermission")
    @ResponseBody
    public Response<String> deleteRolePermission(@RequestParam String role){
        String result = permissionServiceImpl.deleteRolePermission(role);
        if ("Success!".equals(result)) {
            return getSuccessResult(result);
        }
        return getFailResult(500, "Error!");
    }
}
