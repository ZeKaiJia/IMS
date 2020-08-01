package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Student mapper.
 * StudentMapper
 * @author kevin
 */
@Repository
public interface StudentMapper {
    /**
     * 添加方法
     *
     * @param student the student
     */
    void insert(Student student);

    /**
     * 保存功能 删除不存在数据
     *
     * @param stuId the stu id
     */
    void save(Integer stuId);

    /**
     * 删除方法 id
     *
     * @param student the student
     */
    void delete(Student student);

    /**
     * re删除方法
     *
     * @param student the student
     */
    void reDelete(Student student);

    /**
     * 修改方法
     *
     * @param student the student
     */
    void update(Student student);

    /**
     * 单个查找方法 id
     *
     * @param stuId the stu id
     * @return the student
     */
    Student select(Integer stuId);

    /**
     * 全体查找方法
     *
     * @return the list
     */
    List<Student> selectAll();

    /**
     * 多参数多返回查找方法
     *
     * @param student the student
     * @return the list
     */
    List<Student> selectByAllInfo(Student student);

    /**
     * 模糊查找
     *
     * @param stuName the stu name
     * @return the list
     */
    List<Student> selectSimilarName(String stuName);

    /**
     * 管理员专用
     *
     * @return the list
     */
    List<Student> selectGarbage();

    /**
     * 管理员专用搜索全体数据
     *
     * @return the list
     */
    List<Student> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     *
     * @param stuId the stu id
     * @return the student
     */
    Student selectAdminById(Integer stuId);
}
