package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Score mapper.
 * ScoreMapper
 * @author kevin
 */
@Repository
public interface ScoreMapper {
    /**
     * 添加功能
     *
     * @param score the score
     */
    void insert(Score score);

    /**
     * 保存功能 删除不存在数据
     *
     * @param stuId the stu id
     * @param subId the sub id
     */
    void save(@Param("stuId") Integer stuId, @Param("subId") Integer subId);

    /**
     * 删除方法 单科 hashmap
     *
     * @param score the score
     */
    void delete(Score score);

    /**
     * re删除方法 单科 hashmap
     *
     * @param score the score
     */
    void reDelete(Score score);

    /**
     * 删除方法 学生
     *
     * @param stuId the stu id
     */
    void saveAll(@Param("stuId") Integer stuId);

    /**
     * 修改方法 id不可被修改
     *
     * @param score the score
     */
    void update(Score score);

    /**
     * 查找方法 单科 hashmap 精确查找 and
     *
     * @param score the score
     * @return the score
     */
    Score select(Score score);

    /**
     * 全体查找方法
     *
     * @return the list
     */
    List<Score> selectAll();

    /**
     * 查找方法 多参数多返回 精确查找 or
     *
     * @param score the score
     * @return the list
     */
    List<Score> selectByAllInfo(Score score);

    /**
     * 查找方法 及格
     *
     * @param subScore the sub score
     * @return the list
     */
    List<Score> selectPassScore(@Param("subScore") Integer subScore);

    /**
     * 通过学生ID获取
     *
     * @method: get by student id.
     * @param: stuId the stu id
     * @return: by student id
     */
    List<Score> getByStudentId(@Param("stuId") Integer stuId);

    /**
     * 管理员专用
     *
     * @return the list
     */
    List<Score> selectGarbage();

    /**
     * 管理员专用搜索全体数据
     *
     * @return the list
     */
    List<Score> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     *
     * @param stuId the stu id
     * @param subId the sub id
     * @return the score
     */
    Score selectAdminById(@Param("stuId") Integer stuId, @Param("subId") Integer subId);

    /**
     * 管理员专用根据学号搜索定向数据
     *
     * @param stuId the stu id
     * @return the list
     */
    List<Score> selectAdminByStuId(@Param("stuId") Integer stuId);

    /**
     * 管理员专用根据课程号搜索定向数据
     *
     * @param subId the sub id
     * @return the list
     */
    List<Score> selectAdminBySubId(@Param("subId") Integer subId);
}
