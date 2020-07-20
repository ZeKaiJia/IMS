package cn.kevin.ims.controller;

import cn.kevin.ims.model.Score;
import cn.kevin.ims.model.Subject;
import cn.kevin.ims.service.ScoreService;
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
@RequestMapping("/score/")
public class ScoreController extends BaseController {
    @Resource(name = "scoreService")
    private ScoreService scoreService;

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

//    @PostMapping(value = "/deleteAll")
//    @ResponseBody
//    public Response<List<Score>> deleteAll(int id) {
//        List<Score> result = scoreService.deleteAll(id);
//        if (result != null) {
//            return getSuccessResult(result);
//        }
//        return getFailResult(404, "Message not find!");
//    }

    /**
     * update
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

    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Score>> selectAll() {
        List<Score> result = scoreService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectByAllInfo")
    @ResponseBody
    public Response<List<Score>> selectByAllInfo(@RequestBody Score score) {
        List<Score> result = scoreService.selectByAllInfo(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectPassScore")
    @ResponseBody
    public Response<List<Score>> selectPassScore(int score) {
        List<Score> result = scoreService.selectPassScore(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdmin")
    @ResponseBody
    public Response<List<Score>> selectAdmin() {
        List<Score> result = scoreService.selectAdmin();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdminById")
    @ResponseBody
    public Response<Score> selectAdminById(Integer stuId, Integer subId) {
        Score result = scoreService.selectAdminById(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdminByStuId")
    @ResponseBody
    public Response<List<Score>> selectAdminByStuId(Integer stuId) {
        List<Score> result = scoreService.selectAdminByStuId(stuId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @GetMapping(value = "/selectAdminBySubId")
    @ResponseBody
    public Response<List<Score>> selectAdminBySubId(Integer subId) {
        List<Score> result = scoreService.selectAdminBySubId(subId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    public static void main(String[] args) {
        Score score = new Score();
        score.setStuId(1);
        score.setSubId(1);
        score.setSubScore(98);
        System.out.println(JSON.toJSONString(score));
    }
}
