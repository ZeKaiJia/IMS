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
 * The type Score service.
 * ScoreService
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class ScoreService {
    /**
     * The Score mapper.
     */
    @Resource
    private ScoreMapper scoreMapper;

    /**
     * The Scores.
     */
    private List<Score> scores;

    /**
     * The Score.
     */
    private Score score;

    /**
     * Insert score.
     * 插入
     * @param sco the sco
     * @return the score
     */
    public Score insert(Score sco) {
        score = scoreMapper.selectAdminById(sco.getStuId(), sco.getSubId());
        if (score == null) {
            sco.setUtcCreate(DateUtil.currentSecond());
            sco.setUtcModify(DateUtil.currentSecond());
            scoreMapper.insert(sco);
            return sco;
        }
        return null;
    }

    /**
     * Save score.
     * 删除
     * @param stuId the stu id
     * @param subId the sub id
     * @return the score
     */
    public Score save(Integer stuId, Integer subId) {
        Score sco = new Score(stuId, subId);
        score = scoreMapper.select(sco);
        if (score != null) {
            scoreMapper.save(stuId, subId);
        }
        return score;
    }

    /**
     * Delete score.
     * 禁用
     * @param stuId the stu id
     * @param subId the sub id
     * @return the score
     */
    public Score delete(Integer stuId, Integer subId) {
        Score sco = new Score(stuId, subId);
        score = scoreMapper.select(sco);
        if (score != null) {
            score.setUtcModify(DateUtil.currentSecond());
            score.setIsReal(false);
            scoreMapper.delete(score);
        }
        return score;
    }

    /**
     * Re delete score.
     * 恢复禁用
     * @param stuId the stu id
     * @param subId the sub id
     * @return the score
     */
    public Score reDelete(Integer stuId, Integer subId) {
        score = scoreMapper.selectAdminById(stuId, subId);
        if (score != null) {
            score.setUtcModify(DateUtil.currentSecond());
            score.setIsReal(true);
            scoreMapper.reDelete(score);
        }
        return score;
    }

    /**
     * Update score.
     * 更新
     * @param sco the sco
     * @return the score
     */
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

    /**
     * Select score.
     * 查找单个非禁用数据
     * @param stuId the stu id
     * @param subId the sub id
     * @return the score
     */
    public Score select(Integer stuId, Integer subId) {
        score = new Score(stuId, subId);
        return scoreMapper.select(score);
    }

    /**
     * Select all list.
     * 查找所有非禁用数据
     * @return the list
     */
    public List<Score> selectAll() {
        return scoreMapper.selectAll();
    }

    /**
     * Select by all info list.
     * 按任意字段查找非禁用数据
     * @param sco the sco
     * @return the list
     */
    public List<Score> selectByAllInfo(Score sco) {
        return scoreMapper.selectByAllInfo(sco);
    }

    /**
     * Select pass score list.
     * 查找非禁用及格数据
     * @param subScore the sub score
     * @return the list
     */
    public List<Score> selectPassScore(Integer subScore) {
        return scoreMapper.selectPassScore(subScore);
    }

    /**
     * Select garbage list.
     * 查找禁用数据
     * @return the list
     */
    public List<Score> selectGarbage() {
        return scoreMapper.selectGarbage();
    }

    /**
     * Select admin list.
     * 查找所有数据
     * @return the list
     */
    public List<Score> selectAdmin() {
        return scoreMapper.selectAdmin();
    }

    /**
     * Select admin by id score.
     * 查找单个数据
     * @param stuId the stu id
     * @param subId the sub id
     * @return the score
     */
    public Score selectAdminById(Integer stuId, Integer subId) {
        return scoreMapper.selectAdminById(stuId, subId);
    }

    /**
     * Select admin by stu id list.
     * 按学生Id字段查找数据
     * @param stuId the stu id
     * @return the list
     */
    public List<Score> selectAdminByStuId(Integer stuId) {
        return scoreMapper.selectAdminByStuId(stuId);
    }

    /**
     * Select admin by sub id list.
     * 按课程Id字段查找数据
     * @param subId the sub id
     * @return the list
     */
    public List<Score> selectAdminBySubId(Integer subId) {
        return scoreMapper.selectAdminBySubId(subId);
    }
}
