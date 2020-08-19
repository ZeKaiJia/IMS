package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Student;
import cn.kevin.ims.service.implement.StudentServiceImpl;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/student/")
public class StudentController extends BaseController {
    @Resource(name = "studentServiceImpl")
    private StudentServiceImpl studentServiceImpl;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @RequiresPermissions("student:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Student> saveStudentInfo(@NotNull @RequestBody Student student) {
        Student result = studentServiceImpl.saveStudentInfo(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @RequiresPermissions("student:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Student> deleteStudent(String stuId) {
        Student result = studentServiceImpl.deleteStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("student:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Student> disableStudent(String stuId) {
        Student result = studentServiceImpl.disableStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("student:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Student> recoverStudent(String stuId) {
        Student result = studentServiceImpl.recoverStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("student:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Student> updateStudentInfo(@RequestBody Student student) {
        Student result = studentServiceImpl.updateStudentInfo(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("student:selectById")
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Student> selectById(String stuId) {
        Student result = studentServiceImpl.selectById(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("student:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Student>> selectAll() {
        List<Student> result = studentServiceImpl.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("student:selectAnyParam")
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<Student>> selectAnyParam(@RequestBody Student student) {
        List<Student> result = studentServiceImpl.selectAnyParam(student);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
