package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.service.ScoreService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@RestController()
@RequestMapping("/score/")
public class ScoreController extends BaseController {
    @Resource(name = "scoreService")
    private ScoreService scoreService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    /**
     * insert
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Response<List<Score>> save() {
        List<Score> result = scoreService.save();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * delete(in fact it's an update)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Score> delete(int id1, int id2) {
        Score result = scoreService.delete(id1, id2);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public Response<List<Score>> deleteAll(int id) {
        List<Score> result = scoreService.deleteAll(id);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    /**
     * update
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
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
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ResponseBody
    public Response<Score> selectById(int id1, int id2) {
        Score result = scoreService.select(id1, id2);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Score>> selectAll() {
        List<Score> result = scoreService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @RequestMapping(value = "/selectByAllInfo", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Score>> selectByAllInfo(@RequestBody Score score) {
        List<Score> result = scoreService.selectByAllInfo(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not find!");
    }

    @RequestMapping(value = "/selectPassScore", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Score>> selectPassScore(int score) {
        List<Score> result = scoreService.selectPassScore(score);
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
