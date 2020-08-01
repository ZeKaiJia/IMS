package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.service.SubjectService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@CrossOrigin
@RestController()
@RequestMapping("/subject/")
public class SubjectController extends BaseController {
    @Resource(name = "subjectService")
    private SubjectService subjectService;

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
    public Response<Subject> insert(@RequestBody Subject subject) {
        Subject result = subjectService.insert(subject);
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
    public Response<Subject> save(Integer subId) {
        Subject result = subjectService.save(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Massage not find!");
    }

    /**
     * delete(in fact it's an update)
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Subject> delete(Integer subId) {
        Subject result = subjectService.delete(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    /**
     * delete(in fact it's an update)
     */
    @PostMapping(value = "/reDelete")
    @ResponseBody
    public Response<Subject> reDelete(Integer subId) {
        Subject result = subjectService.reDelete(subId);
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
    public Response<Subject> update(@RequestBody Subject subject) {
        Subject result = subjectService.update(subject);
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
    public Response<Subject> selectById(Integer subId) {
        Subject result = subjectService.select(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "ID not find!");
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Subject>> selectAll() {
        List<Subject> result = subjectService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Subject>> selectByAllInfo(@RequestBody Subject subject) {
        List<Subject> result = subjectService.selectByAllInfo(subject);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectSimilarName")
    @ResponseBody
    public Response<List<Subject>> selectSimilarName(String name) {
        List<Subject> result = subjectService.selectSimilarName(name);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdmin")
    @ResponseBody
    public Response<List<Subject>> selectAdmin() {
        List<Subject> result = subjectService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<Subject> selectAdminById(Integer subId) {
        Subject result = subjectService.selectAdminById(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.setSubId(1);
        subject.setSubName("高数");
        subject.setSubTeacherId("2");
        subject.setSubCredit(5.0);
        System.out.println(JSON.toJSONString(subject));
    }
}
