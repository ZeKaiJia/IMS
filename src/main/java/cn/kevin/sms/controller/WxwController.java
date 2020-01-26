package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.service.StudentService;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Maps;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

/**
 * @author Sean Wu
 */
@RestController()
@RequestMapping("/hello/")
public class WxwController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name)  {
        return "hello" + name;
    }


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Student insertUser(@RequestBody Student student)  {
        student.setStuId(new Random().nextInt(500000));
        studentService.insert(student);
        return student;
    }

    public static void main(String[] args) {
        Student stu = new Student();
        stu.setStuId(34);
        stu.setStuGender(1);
        stu.setStuAge(20);
        stu.setStuEmail("1@1");
        stu.setStuName("wulala");
        System.out.println(JSON.toJSONString(stu));
    }

}
