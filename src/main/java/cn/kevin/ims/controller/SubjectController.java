package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.service.implement.SubjectServiceImpl;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/subject/")
public class SubjectController extends BaseController {
    @Resource(name = "subjectServiceImpl")
    private SubjectServiceImpl subjectServiceImpl;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @RequiresPermissions("subject:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Subject> saveSubjectInfo(@RequestBody Subject subject) {
        Subject result = subjectServiceImpl.saveSubjectInfo(subject);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @RequiresPermissions("subject:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Subject> deleteSubject(Integer subId) {
        Subject result = subjectServiceImpl.deleteSubject(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Subject> disableSubject(Integer subId) {
        Subject result = subjectServiceImpl.disableSubject(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Subject> recoverSubject(Integer subId) {
        Subject result = subjectServiceImpl.recoverSubject(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Subject> updateSubjectInfo(@RequestBody Subject subject) {
        Subject result = subjectServiceImpl.updateSubjectInfo(subject);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:selectById")
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Subject> selectById(Integer subId) {
        Subject result = subjectServiceImpl.selectById(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Subject>> selectAll() {
        List<Subject> result = subjectServiceImpl.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("subject:selectByAllInfo")
    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Subject>> selectAnyParam(@RequestBody Subject subject) {
        List<Subject> result = subjectServiceImpl.selectAnyParam(subject);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
