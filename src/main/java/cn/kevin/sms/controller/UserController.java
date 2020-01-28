package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.service.ScoreService;
import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.service.SubjectService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author kevin
 */
@RestController()
@RequestMapping("/hello/")
public class UserController {
    @Resource(name = "studentService")
    private StudentService studentService;
    @Resource(name = "subjectService")
    private SubjectService subjectService;
    @Resource(name = "scoreService")
    private ScoreService scoreService;

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name)  {
        return "hello" + name;
    }


    @RequestMapping(value = "/insStu",method = RequestMethod.POST)
    @ResponseBody
    public Student insertUser(@RequestBody Student student)  {
        student.setStuId(new Random().nextInt(5000));
        studentService.insert(student);
        return student;
    }

    @RequestMapping(value = "/delStu",method = RequestMethod.POST)
    @ResponseBody
    public Object delUser(int id)  {
        studentService.delete(id);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("messge", "删除成功");
        return retMap;
    }

    @RequestMapping(value = "/delStuByName",method = RequestMethod.POST)
    @ResponseBody
    public Object delUserByName(String name)  {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("messge", "删除成功");
        return retMap;
    }
    public static void main(String[] args) {
        Student stu = new Student();
        stu.setStuId(35);
        stu.setStuGender(1);
        stu.setStuBirthday(new Date());
        stu.setStuAge(20);
        stu.setStuEmail("1@1");
        stu.setStuName("wulala");
        System.out.println(JSON.toJSONString(stu));
    }
}
