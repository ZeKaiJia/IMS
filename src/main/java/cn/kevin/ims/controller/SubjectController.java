package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.service.SubjectService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Subject controller.
 * SubjectController
 * @author kevin
 */
@CrossOrigin
@RestController()
@RequestMapping("/subject/")
public class SubjectController extends BaseController {
    /**
     * The Subject service.
     */
    @Resource(name = "subjectService")
    private SubjectService subjectService;

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
     * @param subject the subject
     * @return the response
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
     * 删除
     * @param subId the sub id
     * @return the response
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
     * 禁用
     * @param subId the sub id
     * @return the response
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
     * 恢复禁用
     * @param subId the sub id
     * @return the response
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
     * 更新
     * @param subject the subject
     * @return the response
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
     * 查找单个非禁用数据
     * @param subId the sub id
     * @return the response
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

    /**
     * Select all response.
     * 查找所有非禁用数据
     * @return the response
     */
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Subject>> selectAll() {
        List<Subject> result = subjectService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select by all info response.
     * 按任意字段查找非禁用数据
     * @param subject the subject
     * @return the response
     */
    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Subject>> selectByAllInfo(@RequestBody Subject subject) {
        List<Subject> result = subjectService.selectByAllInfo(subject);
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
    public Response<List<Subject>> selectSimilarName(String name) {
        List<Subject> result = subjectService.selectSimilarName(name);
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
    public Response<List<Subject>> selectAdmin() {
        List<Subject> result = subjectService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin by id response.
     * 查找单个数据
     * @param subId the sub id
     * @return the response
     */
    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<Subject> selectAdminById(Integer subId) {
        Subject result = subjectService.selectAdminById(subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }
}
