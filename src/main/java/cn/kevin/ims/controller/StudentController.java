package cn.kevin.ims.controller;

import cn.kevin.ims.aop.LoginAction;
import cn.kevin.ims.entity.Student;
import cn.kevin.ims.service.StudentService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/student/")
public class StudentController extends BaseController {
    @Resource(name = "studentService")
    private StudentService studentService;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Student> saveStudentInfo(@NotNull @RequestBody Student student) {
        Student result = studentService.saveStudentInfo(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Student> deleteStudent(String stuId) {
        Student result = studentService.deleteStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Student> disableStudent(String stuId) {
        Student result = studentService.disableStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Student> recoverStudent(String stuId) {
        Student result = studentService.recoverStudent(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Student> updateStudentInfo(@RequestBody Student student) {
        Student result = studentService.updateStudentInfo(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Student> selectById(String stuId) {
        Student result = studentService.selectById(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Student>> selectAll() {
        List<Student> result = studentService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
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
