package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.service.ScoreService;
import cn.kevin.ims.vo.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Score> saveScoreInfo(@RequestBody Score score) {
        Score result = scoreService.saveScoreInfo(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Score> deleteScore(String stuId, Integer subId) {
        Score result = scoreService.deleteScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Score> disableScore(String stuId, Integer subId) {
        Score result = scoreService.disableScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Score> recoverScore(String stuId, Integer subId) {
        Score result = scoreService.recoverScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Score> updateScoreInfo(@RequestBody Score score) {
        Score result = scoreService.updateScoreInfo(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectByStudentAndSubjectId")
    @ResponseBody
    public Response<Score> selectByStudentAndSubjectId(String stuId, Integer subId) {
        Score result = scoreService.selectByStudentAndSubjectId(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Score>> selectAll() {
        List<Score> result = scoreService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<Score>> selectAnyParam(@RequestBody Score score) {
        List<Score> result = scoreService.selectAnyParam(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectByStudentId")
    @ResponseBody
    public Response<List<Score>> selectByStudentId(String stuId) {
        List<Score> result = scoreService.selectByStudentId(stuId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @GetMapping(value = "/selectBySubjectId")
    @ResponseBody
    public Response<List<Score>> selectBySubjectId(Integer subId) {
        List<Score> result = scoreService.selectBySubjectId(subId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
