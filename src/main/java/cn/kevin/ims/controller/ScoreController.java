package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.service.implement.ScoreServiceImpl;
import cn.kevin.ims.vo.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/score/")
public class ScoreController extends BaseController {
    @Resource(name = "scoreServiceImpl")
    private ScoreServiceImpl scoreServiceImpl;
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
    @RequiresPermissions("score:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Score> saveScoreInfo(@RequestBody Score score) {
        Score result = scoreServiceImpl.saveScoreInfo(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }
    @RequiresPermissions("score:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Score> deleteScore(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreServiceImpl.deleteScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Score> disableScore(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreServiceImpl.disableScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Score> recoverScore(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreServiceImpl.recoverScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Score> updateScoreInfo(@RequestBody Score score) {
        Score result = scoreServiceImpl.updateScoreInfo(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:selectByStuAndSub")
    @GetMapping(value = "/selectByStudentAndSubjectId")
    @ResponseBody
    public Response<Score> selectByStudentAndSubjectId(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreServiceImpl.selectByStudentAndSubjectId(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Score>> selectAll() {
        List<Score> result = scoreServiceImpl.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:selectAnyParam")
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<Score>> selectAnyParam(@RequestBody Score score) {
        List<Score> result = scoreServiceImpl.selectAnyParam(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:selectByStudentId")
    @GetMapping(value = "/selectByStudentId")
    @ResponseBody
    public Response<List<Score>> selectByStudentId(@RequestParam String stuId) {
        List<Score> result = scoreServiceImpl.selectByStudentId(stuId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
    @RequiresPermissions("score:selectBySubjectId")
    @GetMapping(value = "/selectBySubjectId")
    @ResponseBody
    public Response<List<Score>> selectBySubjectId(@RequestParam Integer subId) {
        List<Score> result = scoreServiceImpl.selectBySubjectId(subId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
