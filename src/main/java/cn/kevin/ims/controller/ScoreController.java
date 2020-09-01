package cn.kevin.ims.controller;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.service.ScoreService;
import cn.kevin.ims.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Api(tags = "成绩模块")
@CrossOrigin
@RestController()
@RequestMapping("/score/")
public class ScoreController extends BaseController {

    @Resource(name = "scoreServiceImpl")
    private ScoreService scoreService;

    @ApiOperation(value = "默认Get请求", notes = "请求不存在的路径时调用此默认接口")
    @ApiImplicitParam(name = "name", value = "默认字段", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/{name}")
    @ResponseBody
    public String helloWorld(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }

    @ApiOperation(value = "添加成绩", notes = "插入一条成绩记录")
    @RequiresPermissions("score:insert")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Response<Score> saveScoreInfo(@RequestBody Score score) {
        Score result = scoreService.saveScoreInfo(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(405, "Message already exist!");
    }

    @ApiOperation(value = "删除成绩", notes = "删除一条成绩记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
    })
    @RequiresPermissions("score:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response<Score> deleteScore(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreService.deleteScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "锁定成绩", notes = "锁定一条成绩记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
    })
    @RequiresPermissions("score:disable")
    @PostMapping(value = "/disable")
    @ResponseBody
    public Response<Score> disableScore(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreService.disableScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "恢复成绩", notes = "恢复一条成绩记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
    })
    @RequiresPermissions("score:recover")
    @PostMapping(value = "/recover")
    @ResponseBody
    public Response<Score> recoverScore(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreService.recoverScore(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "更新成绩", notes = "更新一条成绩记录")
    @RequiresPermissions("score:update")
    @PostMapping(value = "/update")
    @ResponseBody
    public Response<Score> updateScoreInfo(@RequestBody Score score) {
        Score result = scoreService.updateScoreInfo(score);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "按学号和课程号查询成绩", notes = "按学号和课程号查询一条成绩记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
    })
    @RequiresPermissions("score:selectByStuAndSub")
    @GetMapping(value = "/selectByStudentAndSubjectId")
    @ResponseBody
    public Response<Score> selectByStudentAndSubjectId(@RequestParam String stuId, @RequestParam Integer subId) {
        Score result = scoreService.selectByStudentAndSubjectId(stuId, subId);
        if (result != null) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "查询成绩列表", notes = "查询所有成绩记录")
    @RequiresPermissions("score:selectAll")
    @GetMapping(value = "/selectAll")
    @ResponseBody
    public Response<List<Score>> selectAll() {
        List<Score> result = scoreService.selectAll();
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "按任意字段查询成绩", notes = "按任意字段查询一条或多条成绩记录")
    @RequiresPermissions("score:selectAnyParam")
    @GetMapping(value = "/selectAnyParam")
    @ResponseBody
    public Response<List<Score>> selectAnyParam(@RequestBody Score score) {
        List<Score> result = scoreService.selectAnyParam(score);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "按学号查询成绩", notes = "按学号查询一条或多条成绩记录")
    @ApiImplicitParam(name = "stuId", value = "学号", required = true, dataTypeClass = String.class)
    @RequiresPermissions("score:selectByStudentId")
    @GetMapping(value = "/selectByStudentId")
    @ResponseBody
    public Response<List<Score>> selectByStudentId(@RequestParam String stuId) {
        List<Score> result = scoreService.selectByStudentId(stuId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }

    @ApiOperation(value = "按课程号查询成绩", notes = "按课程号查询一条或多条成绩记录")
    @ApiImplicitParam(name = "subId", value = "课程号", required = true, dataTypeClass = Integer.class)
    @RequiresPermissions("score:selectBySubjectId")
    @GetMapping(value = "/selectBySubjectId")
    @ResponseBody
    public Response<List<Score>> selectBySubjectId(@RequestParam Integer subId) {
        List<Score> result = scoreService.selectBySubjectId(subId);
        if (result.size() != 0) {
            return getSuccessResult(result);
        }
        return getFailResult(404, "Message not found!");
    }
}
