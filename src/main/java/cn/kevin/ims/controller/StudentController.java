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

/**
 * The type Student controller.
 * StudentController
 * @author kevin
 */
@CrossOrigin
@RestController()
@RequestMapping("/student/")
public class StudentController extends BaseController {
    /**
     * The Student service.
     */
    @Resource(name = "studentService")
    private StudentService studentService;

    /**
     * Hello world string.
     * 测试
     * @param name the name
     * @return the string
     */
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    /**
     * insert
     * 插入
     * @param student the student
     * @return the response
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Student> insert(@NotNull @RequestBody Student student) {
        Student result = studentService.insert(student);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "ID already exist!");
    }

    /**
     * save(in fact it's delete)
     * 删除
     * @param stuId the stu id
     * @return the response
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Response<Student> save(Integer stuId) {
        Student result = studentService.save(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     * 禁用
     * @param stuId the stu id
     * @return the response
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Student> delete(Integer stuId) {
        Student result = studentService.delete(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * delete(in fact it's an update)
     * 恢复禁用
     * @param stuId the stu id
     * @return the response
     */
    @PostMapping(value = "/reDelete")
    @ResponseBody
    public Response<Student> reDelete(Integer stuId) {
        Student result = studentService.reDelete(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * update
     * 更新
     * @param student the student
     * @return the response
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
     * 查找单个非禁用数据
     * @param id the id
     * @return the response
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

    /**
     * Select all response.
     * 查找所有非禁用数据
     * @return the response
     */
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

    /**
     * Select by all info response.
     * 按任意字段查找非禁用数据
     * @param student the student
     * @return the response
     */
    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Student>> selectByAllInfo(@RequestBody Student student) {
        List<Student> result = studentService.selectByAllInfo(student);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select similar name response.
     * 按姓名字段查找非禁用数据
     * @param name the name
     * @return the response
     */
    @GetMapping(value = "/selectSimilarName")
    @ResponseBody
    public Response<List<Student>> selectSimilarName(String name) {
        List<Student> result = studentService.selectSimilarName(name);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin response.
     * 查找所有数据
     * @return the response
     */
    @GetMapping(value = "/selectAdmin")
    @ResponseBody
    public Response<List<Student>> selectAdmin() {
        List<Student> result = studentService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin by id response.
     * 查找单个数据
     * @param stuId the stu id
     * @return the response
     */
    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<Student> selectAdminById(Integer stuId) {
        Student result = studentService.selectAdminById(stuId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }
}
