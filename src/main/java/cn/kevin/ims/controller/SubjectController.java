package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.service.SubjectService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/subject/")
public class SubjectController extends BaseController {
    @Resource(name = "subjectService")
    private SubjectService subjectService;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @RequiresPermissions("subject:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Subject> saveSubjectInfo(@RequestBody Subject subject) {
        Subject result = subjectService.saveSubjectInfo(subject);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @RequiresPermissions("subject:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Subject> deleteSubject(Integer subId) {
        Subject result = subjectService.deleteSubject(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Subject> disableSubject(Integer subId) {
        Subject result = subjectService.disableSubject(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Subject> recoverSubject(Integer subId) {
        Subject result = subjectService.recoverSubject(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Subject> updateSubjectInfo(@RequestBody Subject subject) {
        Subject result = subjectService.updateSubjectInfo(subject);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:selectById")
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Subject> selectById(Integer subId) {
        Subject result = subjectService.selectById(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Subject>> selectAll() {
        List<Subject> result = subjectService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:selectByAllInfo")
    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Subject>> selectAnyParam(@RequestBody Subject subject) {
        List<Subject> result = subjectService.selectAnyParam(subject);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
