package cn.kevin.ims.controller;

import cn.kevin.ims.aop.LoginAction;
import cn.kevin.ims.model.Student;
import cn.kevin.ims.service.StudentService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author kevin
 */
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

    /**
     * insert
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Student> insert(@NotNull @RequestBody Student student) {
        student.setStuId(new Random().nextInt(5000));
        Student result = studentService.insert(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "ID already exist!");
    }

    /**
     * save(in fact it's delete)
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Response<List<Student>> save() {
        List<Student> result = studentService.save();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Student> delete(int id) {
        Student result = studentService.delete(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * update
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Student> update(@RequestBody Student student) {
        Student result = studentService.update(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * select
     */
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Student> selectById(int id) {
        Student result = studentService.select(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    @LoginAction(value = "false")
    public Response<List<Student>> selectAll() {
        List<Student> result = studentService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Student>> selectByAllInfo(@RequestBody Student student) {
        List<Student> result = studentService.selectByAllInfo(student);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectSimilarName")
    @ResponseBody
    public Response<List<Student>> selectSimilarName(String name) {
        List<Student> result = studentService.selectSimilarName(name);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }


    public static void main(String[] args) {
        Student stu = new Student();
        stu.setStuId(3);
        stu.setStuAge(21);
        stu.setStuBirthday(new Date());
        stu.setStuEmail("jiazekai@gmail.com");
        stu.setStuGender(1);
        stu.setStuName("wulala");
        System.out.println(JSON.toJSONString(stu));
    }
}
