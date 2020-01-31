package cn.kevin.sms.mapper;

import cn.kevin.sms.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author kevin
 */
@Mapper
public interface StudentMapper {
    //添加方法
    void insert(Student student);

    //保存功能 删除不存在数据
    void save();

    //删除方法 id
    void delete(Map<String, Object> map);

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

    //管理员专用
    List<Student> selectGarbage();
}
