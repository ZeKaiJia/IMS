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

/**
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class ScoreService {
    @Resource
    private ScoreMapper scoreMapper;

    private List<Score> scores;

    private Score score;

    public Score insert(Score sco) {
        score = scoreMapper.select(sco);
        if (score == null) {
            long currentSecond = DateUtil.currentSecond();
            sco.setUtcCreate(currentSecond);
            sco.setUtcModify(currentSecond);
            scoreMapper.insert(sco);
            return sco;
        }
        return null;
    }

    public List<Score> save() {
        scores = scoreMapper.selectGarbage();
        if (scores.size() != 0) {
            scoreMapper.save();
        }
        return scores;
    }

    public Score delete(Integer stuId, Integer subId) {
        score = new Score(stuId, subId);
        score = scoreMapper.select(score);
        if (score != null) {
            score.setUtcModify(DateUtil.currentSecond());
            score.setIsReal(false);
            scoreMapper.delete(score);
        }
        return score;
    }

    public List<Score> deleteAll(Integer stuId) {
        score = new Score();
        score.setStuId(stuId);
        scores = scoreMapper.selectByAllInfo(score);
        if (scores.size() != 0) {
            for (Score sco : scores) {
                sco.setUtcModify(DateUtil.currentSecond());
                sco.setIsReal(false);
            }
            score.setUtcModify(DateUtil.currentSecond());
            scoreMapper.deleteAll(score);
        }
        return scores;
    }

    public Score update(Score sco) {
        score = scoreMapper.select(sco);
        if (score != null) {
            sco.setUtcCreate(score.getUtcCreate());
            sco.setUtcModify(DateUtil.currentSecond());
            scoreMapper.update(sco);
            return sco;
        }
        return null;
    }

    public Score select(Integer stuId, Integer subId) {
        score = new Score(stuId, subId);
        return scoreMapper.select(score);
    }

    public List<Score> selectAll() {
        return scoreMapper.selectAll();
    }

    public List<Score> selectByAllInfo(Score sco) {
        return scoreMapper.selectByAllInfo(sco);
    }

    public List<Score> selectPassScore(Integer subScore) {
        return scoreMapper.selectPassScore(subScore);
    }

    public List<Score> selectGarbage() {
        return scoreMapper.selectGarbage();
    }
}
