package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.service.ScoreService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Score controller.
 * ScoreController
 * @author kevin
 */
@CrossOrigin
@RestController()
@RequestMapping("/score/")
public class ScoreController extends BaseController {
    /**
     * The Score service.
     */
    @Resource(name = "scoreService")
    private ScoreService scoreService;

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
     * @param score the score
     * @return the response
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Score> insert(@RequestBody Score score) {
        if (checkIllegalityScore(score.getSubScore())) {
            return getFailResult(405, "Score must be in the range [0,100]!");
        }
        Score result = scoreService.insert(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }

    /**
     * save(in fact it's delete)
     * 删除
     * @param stuId the stu id
     * @param subId the sub id
     * @return the response
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Response<Score> save(Integer stuId, Integer subId) {
        Score result = scoreService.save(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     * 禁用
     * @param stuId the stu id
     * @param subId the sub id
     * @return the response
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Score> delete(Integer stuId, Integer subId) {
        Score result = scoreService.delete(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     * 恢复禁用
     * @param stuId the stu id
     * @param subId the sub id
     * @return the response
     */
    @PostMapping(value = "/reDelete")
    @ResponseBody
    public Response<Score> reDelete(Integer stuId, Integer subId) {
        Score result = scoreService.reDelete(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * update
     * 更新
     * @param score the score
     * @return the response
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Score> update(@RequestBody Score score) {
        if (checkIllegalityScore(score.getSubScore())) {
            return getFailResult(405, "Score must be in the range [0,100]!");
        }
        Score result = scoreService.update(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * select
     * 查找单个非禁用数据
     * @param stuId the stu id
     * @param subId the sub id
     * @return the response
     */
    @GetMapping(value = "/selectById")
    @ResponseBody
    public Response<Score> selectById(Integer stuId, Integer subId) {
        Score result = scoreService.select(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select all response.
     * 查找所有非禁用数据
     * @return the response
     */
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Score>> selectAll() {
        List<Score> result = scoreService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select by all info response.
     * 按字段查找非禁用数据
     * @param score the score
     * @return the response
     */
    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Score>> selectByAllInfo(@RequestBody Score score) {
        List<Score> result = scoreService.selectByAllInfo(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select pass score response.
     * 查找非禁用及格数据
     * @param score the score
     * @return the response
     */
    @GetMapping(value = "/selectPassScore")
    @ResponseBody
    public Response<List<Score>> selectPassScore(int score) {
        List<Score> result = scoreService.selectPassScore(score);
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
    public Response<List<Score>> selectAdmin() {
        List<Score> result = scoreService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin by id response.
     * 查找单个数据
     * @param stuId the stu id
     * @param subId the sub id
     * @return the response
     */
    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<Score> selectAdminById(Integer stuId, Integer subId) {
        Score result = scoreService.selectAdminById(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin by stu id response.
     * 按学号查找
     * @param stuId the stu id
     * @return the response
     */
    @GetMapping(value = "/selectAdminByStuId")
    @ResponseBody
    public Response<List<Score>> selectAdminByStuId(Integer stuId) {
        List<Score> result = scoreService.selectAdminByStuId(stuId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * Select admin by sub id response.
     * 按课程号查找
     * @param subId the sub id
     * @return the response
     */
    @GetMapping(value = "/selectAdminBySubId")
    @ResponseBody
    public Response<List<Score>> selectAdminBySubId(Integer subId) {
        List<Score> result = scoreService.selectAdminBySubId(subId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }
}
