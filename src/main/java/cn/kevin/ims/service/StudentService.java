package cn.kevin.ims.service;

import cn.kevin.ims.entity.Student;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The interface Student service.
 * 学生表Service层接口类
 * @Author: Kevin
 * @Date: 2020 /8/19 5:31 下午
 */
public interface StudentService {
    /**
     * Save student info student.
     * 根据学生对象插入学生数据
     * @param paramStudent the param student
     * @return the student
     */
    Student saveStudentInfo(@NotNull Student paramStudent);

    /**
     * Delete student student.
     * 根据学号删除学生
     * @param paramStudentId the param student id
     * @return the student
     */
    Student deleteStudent(String paramStudentId);

    /**
     * Disable student student.
     * 根据学号禁用学生
     * @param paramStudentId the param student id
     * @return the student
     */
    Student disableStudent(String paramStudentId);

    /**
     * Recover student student.
     * 根据学号恢复学生
     * @param paramStudentId the param student id
     * @return the student
     */
    Student recoverStudent(String paramStudentId);

    /**
     * Update student info student.
     * 根据学生对象更新学生数据
     * @param paramStudent the param student
     * @return the student
     */
    Student updateStudentInfo(@NotNull Student paramStudent);

    /**
     * Select all list.
     * 查找学生列表
     * @return the list
     */
    List<Student> selectAll();

    /**
     * Select any param list.
     * 根据任意字段查询学生数据
     * @param paramStudent the param student
     * @return the list
     */
    List<Student> selectAnyParam(Student paramStudent);

    /**
     * Select by id student.
     * 根据学号查询学生数据
     * @param paramStudentId the param student id
     * @return the student
     */
    Student selectById(String paramStudentId);
}
