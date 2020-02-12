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

    private Map<String, Object> map;

    public Score insert(Score sco) {
        map = new HashMap<>(4);
        map.put("stuId",sco.getStuId());
        map.put("subId",sco.getSubId());
        score = scoreMapper.select(map);
        if ( score == null ) {
            sco.setGmtCreate(DateUtils.currentSecond());
            sco.setGmtModify(DateUtils.currentSecond());
            scoreMapper.insert(sco);
            return sco;
        }
        return null;
    }

    public List<Score> save() {
        scores = scoreMapper.selectGarbage();
        if ( scores.size() != 0 ) {
            for (Score sco : scores) {
                sco.setGmtModify(DateUtils.currentSecond());
            }
            scoreMapper.save();
        }
        return scores;
    }

    public Score delete(Integer stuId, Integer subId) {
        map = new HashMap<>(4);
        map.put("stuId",stuId);
        map.put("subId",subId);
        score = scoreMapper.select(map);
        if ( score != null ) {
            map.put("gmtModify", DateUtils.currentSecond());
            scoreMapper.delete(map);
            score.setIsReal(false);
        }
        return score;
    }

    public List<Score> deleteAll(Integer stuId) {
        map = new HashMap<>(4);
        map.put("stuId",stuId);
        score = new Score();
        score.setStuId(stuId);
        scores = scoreMapper.selectByAllInfo(score);
        if ( scores.size() != 0 ) {
            for (Score sco: scores) {
                sco.setGmtModify(DateUtils.currentSecond());
                sco.setIsReal(false);
            }
            map.put("gmtModify", DateUtils.currentSecond());
            scoreMapper.deleteAll(map);
        }
        return scores;
    }

    public Score update(Score sco) {
        map = new HashMap<>(4);
        map.put("stuId",sco.getStuId());
        map.put("subId",sco.getSubId());
        score = scoreMapper.select(map);
        if (score != null) {
            sco.setGmtCreate(score.getGmtCreate());
            sco.setGmtModify(DateUtils.currentSecond());
            scoreMapper.update(sco);
            return sco;
        }
        return null;
    }

    public Score select(Integer stuId, Integer subId) {
        map = new HashMap<>(4);
        map.put("stuId",stuId);
        map.put("subId",subId);
        score = scoreMapper.select(map);
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
