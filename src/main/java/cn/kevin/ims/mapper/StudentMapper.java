package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    void saveStudentInfo(Student student);
    void deleteStudent(@Param("stuId") String stuId);
    void disableStudent(Student student);
    void recoverStudent(Student student);
    void updateStudentInfo(Student student);
    List<Student> selectAll();
    List<Student> selectAnyParam(Student student);
    Student selectById(@Param("stuId") String stuId);
}
