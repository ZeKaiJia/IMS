package cn.kevin.stu.manage.mapper;

import cn.kevin.stu.manage.entity.Subject;

import java.util.List;

/**
 * @author kevin
 */
public interface SubjectMapper {
    //添加方法
    void addSubject(Subject subject);

    //删除方法 id
    void deleteSubjectById(Integer subId);

    //修改方法
    void updateSubject(Subject subject);

    //单个查找方法 id
    Subject selectSubject(Integer subId);

    //全体查找方法
    List<Subject> selectAllSubject();
}
