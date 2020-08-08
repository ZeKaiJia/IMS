package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Role;
import cn.kevin.ims.service.PermissionService;
import cn.kevin.ims.service.RoleService;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController()
@RequestMapping("/role/")
public class RoleController extends BaseController {
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "permissionService")
    private PermissionService permissionService;
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
    @GetMapping(value = "/selectAllUserRole")
    @ResponseBody
    public Response<List<String>> selectAllUserRole() {
        List<String> result = roleService.selectAllUserRole();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Role>> selectAll() {
        List<Role> result = roleService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Role> selectById(@RequestParam Integer id) {
        Role result = roleService.selectById(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
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
    @GetMapping(value = "/findPermissionByRole")
    @ResponseBody
    public Response<Set<String>> findPermissionByRole(@RequestParam String role) {
        Set<String> results = permissionService.findPermissionByRole(role);
        if (results.size() != 0) {
            return getSuccessResult(results);
        }
        return getFailResult(404, "MEssage not found!");
    }
}
