package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {
    void saveScoreInfo(Score score);
    void deleteScore(@Param("stuId") String stuId, @Param("subId") Integer subId);
    void disableScore(Score score);
    void recoverScore(Score score);
    void updateScoreInfo(Score score);
    Score selectByStudentAndSubjectId(@Param("stuId") String stuId, @Param("subId") Integer subId);
    List<Score> selectAll();
    List<Score> selectAnyParam(Score score);
    List<Score> selectByStudentId(@Param("stuId") String stuId);
    List<Score> selectBySubjectId(@Param("subId") Integer subId);
}
