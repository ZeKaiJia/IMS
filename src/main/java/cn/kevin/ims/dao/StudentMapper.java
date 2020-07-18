package cn.kevin.ims.dao;

import cn.kevin.ims.model.Student;
import cn.kevin.ims.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kevin
 */
@Repository
public interface StudentMapper {
    /**
     * 添加方法
     */
    void insert(Student student);

    /**
     * 保存功能 删除不存在数据
     */
    void save(Integer stuId);

    /**
     * 删除方法 id
     */
    void delete(Student student);

    /**
     * re删除方法
     */
    void reDelete(Student student);

    /**
     * 修改方法
     */
    void update(Student student);

    /**
     * 单个查找方法 id
     */
    Student select(Integer stuId);

    /**
     * 全体查找方法
     */
    List<Student> selectAll();

    /**
     * 多参数多返回查找方法
     */
    List<Student> selectByAllInfo(Student student);

    /**
     * 模糊查找
     */
    List<Student> selectSimilarName(String stuName);

    /**
     * 管理员专用
     */
    List<Student> selectGarbage();

    /**
     * 管理员专用搜索全体数据
     */
    List<Student> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     */
    Student selectAdminById(Integer stuId);
}
