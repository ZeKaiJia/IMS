package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.service.StudentService;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Maps;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Sean Wu
 */
@RestController()
@RequestMapping("/hello/")
public class WxwController extends BaseController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name)  {
        return "hello" + name;
    }

    @Override
    void doBiz(int choice) {
        // do nothing!
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Object insertUser(@RequestBody Student student)  {
        student.setStuId(new Random().nextInt(500000));
        studentService.insert(student);
        return getSuccessResult(student);
    }


    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public Object delUser(int sid)  {
         studentService.delete(sid);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("messge", "删除成功");
        return retMap;
    }


    @RequestMapping(value = "/delUserByName",method = RequestMethod.POST)
    @ResponseBody
    public Object delUserByName(String name)  {
        // studentService.delete(sid);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("messge", "删除成功");
        return retMap;
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
