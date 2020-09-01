package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Student;
import cn.kevin.ims.service.StudentService;
import cn.kevin.ims.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Api(tags = "学生模块")
@CrossOrigin
@RestController()
@RequestMapping("/student/")
public class StudentController extends BaseController {

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @ApiOperation(value = "默认Get请求", notes = "请求不存在的路径时调用此默认接口")
    @ApiImplicitParam(name = "name", value = "默认字段", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    @ApiOperation(value = "添加学生", notes = "插入一条学生记录")
    @RequiresPermissions("student:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Student> saveStudentInfo(@NotNull @RequestBody Student student) {
        Student result = studentService.saveStudentInfo(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }

    @ApiOperation(value = "删除学生", notes = "删除一条学生记录")
    @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class)
    @RequiresPermissions("student:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Student> deleteStudent(String stuId) {
        Student result = studentService.deleteStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "锁定学生", notes = "锁定一条学生记录")
    @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class)
    @RequiresPermissions("student:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Student> disableStudent(String stuId) {
        Student result = studentService.disableStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "恢复学生", notes = "恢复一条学生记录")
    @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class)
    @RequiresPermissions("student:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Student> recoverStudent(String stuId) {
        Student result = studentService.recoverStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "更新学生", notes = "更新一条学生记录")
    @RequiresPermissions("student:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Student> updateStudentInfo(@RequestBody Student student) {
        Student result = studentService.updateStudentInfo(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "按学号查询学生", notes = "按学号查询一条学生记录")
    @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class)
    @RequiresPermissions("student:selectById")
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Student> selectById(String stuId) {
        Student result = studentService.selectById(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "查询学生列表", notes = "查询所有学生记录")
    @RequiresPermissions("student:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Student>> selectAll() {
        List<Student> result = studentService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "按任意字段查询学生", notes = "按任意字段查询一条或多条学生记录")
    @RequiresPermissions("student:selectAnyParam")
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<Student>> selectAnyParam(@RequestBody Student student) {
        List<Student> result = studentService.selectAnyParam(student);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
