package cn.kevin.ims.service.implement;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.mapper.ScoreMapper;
import cn.kevin.ims.service.ScoreService;
import cn.kevin.ims.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * The type Score service.
 * 成绩表Service层接口实现类
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreMapper scoreMapper;
    private Score score;
    @Override
    public Score saveScoreInfo(@NotNull Score paramScore) {
        score = scoreMapper.selectByStudentAndSubjectId(paramScore.getStuId(), paramScore.getSubId());
        if (score == null) {
            paramScore.setUtcCreate(DateUtil.currentSecond());
            paramScore.setUtcModify(DateUtil.currentSecond());
            scoreMapper.saveScoreInfo(paramScore);
            return paramScore;
        } else {
            return null;
        }
    }
    @Override
    public Score deleteScore(String paramStudentId, Integer paramSubjectId) {
        score = scoreMapper.selectByStudentAndSubjectId(paramStudentId, paramSubjectId);
        if (score != null) {
            scoreMapper.deleteScore(paramStudentId, paramSubjectId);
            return score;
        } else {
            return null;
        }
    }
    @Override
    public Score disableScore(String paramStudentId, Integer paramSubjectId) {
        score = scoreMapper.selectByStudentAndSubjectId(paramStudentId, paramSubjectId);
        if (score != null) {
            score.setUtcModify(DateUtil.currentSecond());
            score.setValid(false);
            scoreMapper.disableScore(score);
            return score;
        } else {
            return null;
        }
    }
    @Override
    public Score recoverScore(String paramStudentId, Integer paramSubjectId) {
        score = scoreMapper.selectByStudentAndSubjectId(paramStudentId, paramSubjectId);
        if (score != null) {
            score.setUtcModify(DateUtil.currentSecond());
            score.setValid(true);
            scoreMapper.recoverScore(score);
            return score;
        } else {
            return null;
        }
    }
    @Override
    public Score updateScoreInfo(@NotNull Score paramScore) {
        score = scoreMapper.selectByStudentAndSubjectId(paramScore.getStuId(), paramScore.getSubId());
        if (score != null) {
            paramScore.setUtcModify(DateUtil.currentSecond());
            scoreMapper.updateScoreInfo(paramScore);
            return paramScore;
        } else {
            return null;
        }
    }
    @Override
    public Score selectByStudentAndSubjectId(String paramStudentId, Integer paramSubjectId) {
        return scoreMapper.selectByStudentAndSubjectId(paramStudentId, paramSubjectId);
    }
    @Override
    public List<Score> selectAll() {
        return scoreMapper.selectAll();
    }
    @Override
    public List<Score> selectAnyParam(Score paramScore) {
        return scoreMapper.selectAnyParam(paramScore);
    }
    @Override
    public List<Score> selectByStudentId(String paramStudentId) {
        return scoreMapper.selectByStudentId(paramStudentId);
    }
    @Override
    public List<Score> selectBySubjectId(Integer paramSubjectId) {
        return scoreMapper.selectBySubjectId(paramSubjectId);
    }
}
