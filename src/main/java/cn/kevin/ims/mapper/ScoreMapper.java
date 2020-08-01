package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kevin
 */
@Repository
public interface ScoreMapper {
    /**
     * 添加功能
     */
    void insert(Score score);

    /**
     * 保存功能 删除不存在数据
     */
    void save(@Param("stuId") Integer stuId, @Param("subId") Integer subId);

    /**
     * 删除方法 单科 hashmap
     */
    void delete(Score score);

    /**
     * re删除方法 单科 hashmap
     */
    void reDelete(Score score);

    /**
     * 删除方法 学生
     */
    void saveAll(@Param("stuId") Integer stuId);

    /**
     * 修改方法 id不可被修改
     */
    void update(Score score);

    /**
     * 查找方法 单科 hashmap 精确查找 and
     */
    Score select(Score score);

    /**
     * 全体查找方法
     */
    List<Score> selectAll();

    /**
     * 查找方法 多参数多返回 精确查找 or
     */
    List<Score> selectByAllInfo(Score score);

    /**
     * 查找方法 及格
     */
    List<Score> selectPassScore(@Param("subScore") Integer subScore);

    /**
     * 通过学生ID获取
     */
    List<Score> getByStudentId(@Param("stuId") Integer stuId);

    /**
     * 管理员专用
     */
    List<Score> selectGarbage();

    /**
     * 管理员专用搜索全体数据
     */
    List<Score> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     */
    Score selectAdminById(@Param("stuId") Integer stuId, @Param("subId") Integer subId);

    /**
     * 管理员专用根据学号搜索定向数据
     */
    List<Score> selectAdminByStuId(@Param("stuId") Integer stuId);

    /**
     * 管理员专用根据课程号搜索定向数据
     */
    List<Score> selectAdminBySubId(@Param("subId") Integer subId);
}
