package cn.kevin.ims.dao;

import cn.kevin.ims.model.Score;
import org.apache.ibatis.annotations.Mapper;
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
    void save();

    /**
     * 删除方法 单科 hashmap
     */
    void delete(Score score);

    /**
     * 删除方法 学生
     */
    void deleteAll(Score score);

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
    List<Score> selectPassScore(Integer subScore);

    /**
     * 通过学生ID获取
     */
    List<Score> getByStudentId(Integer stuId);

    /**
     * 管理员专用
     */
    List<Score> selectGarbage();
}
