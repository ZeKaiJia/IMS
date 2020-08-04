package cn.kevin.ims.service;

import cn.kevin.ims.entity.Score;
import cn.kevin.ims.mapper.ScoreMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class ScoreService {
    @Resource
    private ScoreMapper scoreMapper;
    private List<Score> scores;
    private Score score;
    public Score saveScoreInfo(Score paramScore) {
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
    public Score deleteScore(String paramStudentId, Integer paramSubjectId) {
        score = scoreMapper.selectByStudentAndSubjectId(paramStudentId, paramSubjectId);
        if (score != null) {
            scoreMapper.deleteScore(paramStudentId, paramSubjectId);
            return score;
        } else {
            return null;
        }
    }
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
    public Score updateScoreInfo(Score paramScore) {
        score = scoreMapper.selectByStudentAndSubjectId(paramScore.getStuId(), paramScore.getSubId());
        if (score != null) {
            paramScore.setUtcModify(DateUtil.currentSecond());
            scoreMapper.updateScoreInfo(paramScore);
            return paramScore;
        } else {
            return null;
        }
    }
    public Score selectByStudentAndSubjectId(String paramStudentId, Integer paramSubjectId) {
        return scoreMapper.selectByStudentAndSubjectId(paramStudentId, paramSubjectId);
    }
    public List<Score> selectAll() {
        return scoreMapper.selectAll();
    }
    public List<Score> selectAnyParam(Score paramScore) {
        return scoreMapper.selectAnyParam(paramScore);
    }
    public List<Score> selectByStudentId(String paramStudentId) {
        return scoreMapper.selectByStudentId(paramStudentId);
    }
    public List<Score> selectBySubjectId(Integer paramSubjectId) {
        return scoreMapper.selectBySubjectId(paramSubjectId);
    }
}
