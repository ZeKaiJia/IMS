package cn.kevin.sms.mapper;

import cn.kevin.sms.entity.Score;

import java.util.List;
import java.util.Map;

/**
 * @author kevin
 */
public interface ScoreMapper {
    //添加方法
    void insert(Score score);

    //删除方法 单科 hashmap
    void delete(Map<String, Object> map);

    //删除方法 学生
    void deleteAll(Integer stuId);

    //修改方法 id不可被修改
    void update(Score score);

    //查找方法 单科 hashmap 精确查找 and
    Score select(Map<String, Object> map);

    //全体查找方法
    List<Score> selectAll();

    //查找方法 多参数多返回 精确查找 or
    List<Score> selectByAllInfo(Score score);

    //查找方法 及格
    List<Score> selectPassScore(Integer subScore);
}
