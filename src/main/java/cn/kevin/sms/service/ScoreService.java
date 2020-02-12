package cn.kevin.sms.service;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.mapper.ScoreMapper;
import cn.kevin.sms.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author kevin
 */
@Service
public class ScoreService {
    @Resource
    private ScoreMapper scoreMapper;

    private List<Score> scores;

    private Score score;

    public Score insert(Score sco) {
        score = scoreMapper.select(sco);
        if ( score == null ) {
            long currentSecond = DateUtils.currentSecond();
            sco.setGmtCreate(currentSecond);
            sco.setGmtModify(currentSecond);
            scoreMapper.insert(sco);
            return sco;
        }
        return null;
    }

    public List<Score> save() {
        scores = scoreMapper.selectGarbage();
        if ( scores.size() != 0 ) {
            scoreMapper.save();
        }
        return scores;
    }

    public Score delete(Integer stuId, Integer subId) {
        score = new Score(stuId, subId);
        score = scoreMapper.select(score);
        if ( score != null ) {
            score.setGmtModify(DateUtils.currentSecond());
            score.setIsReal(false);
            scoreMapper.delete(score);
        }
        return score;
    }

    public List<Score> deleteAll(Integer stuId) {
        score = new Score();
        score.setStuId(stuId);
        scores = scoreMapper.selectByAllInfo(score);
        if ( scores.size() != 0 ) {
            for (Score sco: scores) {
                sco.setGmtModify(DateUtils.currentSecond());
                sco.setIsReal(false);
            }
            score.setGmtModify(DateUtils.currentSecond());
            scoreMapper.deleteAll(score);
        }
        return scores;
    }

    public Score update(Score sco) {
        score = scoreMapper.select(sco);
        if (score != null) {
            sco.setGmtCreate(score.getGmtCreate());
            sco.setGmtModify(DateUtils.currentSecond());
            scoreMapper.update(sco);
            return sco;
        }
        return null;
    }

    public Score select(Integer stuId, Integer subId) {
        score = new Score(stuId, subId);
        score = scoreMapper.select(score);
        return score;
    }

    public List<Score> selectAll() {
        scores = scoreMapper.selectAll();
        return scores;
    }

    public List<Score> selectByAllInfo(Score sco) {
        scores = scoreMapper.selectByAllInfo(sco);
        return scores;
    }

    public List<Score> selectPassScore(Integer subScore) {
        scores = scoreMapper.selectPassScore(subScore);
        return scores;
    }
}
