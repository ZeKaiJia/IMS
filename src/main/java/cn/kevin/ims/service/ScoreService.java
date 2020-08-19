package cn.kevin.ims.service;

import cn.kevin.ims.entity.Score;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The interface Score service.
 * 成绩表Service层接口类
 * @Author: Kevin
 * @Date: 2020 /8/19 5:59 下午
 */
public interface ScoreService {
    /**
     * Save score info score.
     * 根据成绩对象插入成绩数据
     * @param paramScore the param score
     * @return the score
     */
    Score saveScoreInfo(@NotNull Score paramScore);

    /**
     * Delete score score.
     * 根据学号和课程号删除成绩数据
     * @param paramStudentId the param student id
     * @param paramSubjectId the param subject id
     * @return the score
     */
    Score deleteScore(String paramStudentId, Integer paramSubjectId);

    /**
     * Disable score score.
     * 根据学号和课程号禁用成绩
     * @param paramStudentId the param student id
     * @param paramSubjectId the param subject id
     * @return the score
     */
    Score disableScore(String paramStudentId, Integer paramSubjectId);

    /**
     * Recover score score.
     * 根据学号和课程号恢复成绩
     * @param paramStudentId the param student id
     * @param paramSubjectId the param subject id
     * @return the score
     */
    Score recoverScore(String paramStudentId, Integer paramSubjectId);

    /**
     * Update score info score.
     * 根据成绩对象更新成绩数据
     * @param paramScore the param score
     * @return the score
     */
    Score updateScoreInfo(@NotNull Score paramScore);

    /**
     * Select by student and subject id score.
     * 根据学号和课程号查找成绩数据
     * @param paramStudentId the param student id
     * @param paramSubjectId the param subject id
     * @return the score
     */
    Score selectByStudentAndSubjectId(String paramStudentId, Integer paramSubjectId);

    /**
     * Select all list.
     * 查询成绩列表
     * @return the list
     */
    List<Score> selectAll();

    /**
     * Select any param list.
     * 根据任意字段查找成绩数据
     * @param paramScore the param score
     * @return the list
     */
    List<Score> selectAnyParam(Score paramScore);

    /**
     * Select by student id list.
     * 根据学号查找成绩
     * @param paramStudentId the param student id
     * @return the list
     */
    List<Score> selectByStudentId(String paramStudentId);

    /**
     * Select by subject id list.
     * 根据课程号查找成绩
     * @param paramSubjectId the param subject id
     * @return the list
     */
    List<Score> selectBySubjectId(Integer paramSubjectId);

}
