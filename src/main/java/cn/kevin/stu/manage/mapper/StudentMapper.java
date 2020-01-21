package cn.kevin.stu.manage.mapper;

import cn.kevin.stu.manage.entity.Student;

import java.util.List;

/**
 * @author kevin
 */
public interface StudentMapper {
    //添加方法
    void addStudent(Student student);

    //删除方法 id
    void deleteStudentById(Integer stuId);

    //修改方法
    void updateStudent(Student student);

    //单个查找方法 id
    Student selectStudent(Integer stuId);

    //全体查找方法
    List<Student> selectAllStudent();
}
