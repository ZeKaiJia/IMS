package cn.kevin.sms.service;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.mapper.ScoreMapper;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author kevin
 */
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
            scoreMapper.insert(sco);
            return sco;
        }
        return null;
    }

    public Score delete(Integer stuId, Integer subId) {
        map = new HashMap<>(4);
        map.put("stuId",stuId);
        map.put("subId",subId);
        score = scoreMapper.select(map);
        if ( score != null ) {
            scoreMapper.delete(map);
        }
        return score;
    }

    public List<Score> deleteAll(Integer stuId) {
        score = new Score();
        score.setStuId(stuId);
        scores = scoreMapper.selectByAllInfo(score);
        if ( scores.size() != 0 ) {
            scoreMapper.deleteAll(stuId);
        }
        return scores;
    }

    public Score update(Score sco) {
        map = new HashMap<>(4);
        map.put("stuId",sco.getStuId());
        map.put("subId",sco.getSubId());
        score = scoreMapper.select(map);
        sco.setSubName(score.getSubName());
        sco.setGmtCreate(score.getGmtCreate());
        if (score != null) {
            scoreMapper.update(sco);
            return sco;
        }
        return null;
    }

    public Score select(Integer stuId, Integer subId) {
        map = new HashMap<>();
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
