package cn.kevin.sms.mapper;

import cn.kevin.sms.entity.Student;

import java.util.List;

/**
 * @author kevin
 */
public interface StudentMapper {
    //添加方法
    void insert(Student student);

    //删除方法 id
    void deleteById(Integer stuId);

    //修改方法
    void update(Student student);

    //单个查找方法 id
    Student select(Integer stuId);

    //全体查找方法
    List<Student> selectAll();

    //多参数多返回查找方法
    List<Student> selectByAllInfo(Student student);

    //模糊查找
    List<Student> selectSimilarName(String stuName);
}
