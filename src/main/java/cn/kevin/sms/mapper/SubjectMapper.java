package cn.kevin.sms.mapper;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author kevin
 */
@Mapper
public interface SubjectMapper {
    //添加方法
    void insert(Subject subject);

    //保存功能 删除不存在数据
    void save();

    //删除方法 id
    void delete(Map<String, Object> map);

    //修改方法
    void update(Subject subject);

    //单个查找方法 id
    Subject select(Integer subId);

    //全体查找方法
    List<Subject> selectAll();

    //多参数多返回查找方法
    List<Subject> selectByAllInfo(Subject subject);

    //模糊查找
    List<Subject> selectSimilarName(String subName);

    //管理员专用
    List<Subject> selectGarbage();
}
