package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.service.SubjectService;
import cn.kevin.ims.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Api(tags = "课程模块")
@CrossOrigin
@RestController()
@RequestMapping("/subject/")
public class SubjectController extends BaseController {

    @Resource(name = "subjectServiceImpl")
    private SubjectService subjectService;

    @ApiOperation(value = "默认Get请求", notes = "请求不存在的路径时调用此默认接口")
    @ApiImplicitParam(name = "name", value = "默认字段", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    @ApiOperation(value = "添加课程", notes = "插入一条课程记录")
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

    @ApiOperation(value = "删除课程", notes = "删除一条课程记录")
    @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
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

    @ApiOperation(value = "锁定课程", notes = "锁定一条课程记录")
    @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
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

    @ApiOperation(value = "恢复课程", notes = "恢复一条课程记录")
    @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
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

    @ApiOperation(value = "更新课程", notes = "更新一条课程记录")
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

    @ApiOperation(value = "按课程号查询课程", notes = "按课程号查询一条课程记录")
    @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
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

    @ApiOperation(value = "查询课程列表", notes = "查询所有课程记录")
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

    @ApiOperation(value = "按任意字段查询课程", notes = "按任意字段查询一条或多条课程记录")
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
