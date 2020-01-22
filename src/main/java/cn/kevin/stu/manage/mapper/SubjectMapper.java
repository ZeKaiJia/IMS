package cn.kevin.stu.manage.mapper;

import cn.kevin.stu.manage.entity.Student;
import cn.kevin.stu.manage.entity.Subject;

import java.util.List;

/**
 * @author kevin
 */
public interface SubjectMapper {
    //添加方法
    void insert(Subject subject);

    //删除方法 id
    void delete(Integer subId);

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
}
