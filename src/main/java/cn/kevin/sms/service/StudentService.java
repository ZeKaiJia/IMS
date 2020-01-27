package cn.kevin.sms.service;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.mapper.StudentMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    private List<Student> students;

    private Student student;

    public Student insert(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if ( student == null ) {
            studentMapper.insert(stu);
            return stu;
        }
        return null;
    }

    public Student delete(Integer stuId) {
        student = studentMapper.select(stuId);
        if ( student != null ) {
            studentMapper.delete(stuId);
        }
        return student;
    }

    public Student update(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student != null) {
            studentMapper.update(stu);
            return stu;
        }
        return null;
    }

    public Student select(Integer stuId) {
        student = studentMapper.select(stuId);
        return student;
    }

    public List<Student> selectAll() {
        students = studentMapper.selectAll();
        return students;
    }

    public List<Student> selectByAllInfo(Student student) {
        students = studentMapper.selectByAllInfo(student);
        return students;
    }

    public List<Student> selectSimilarName(String stuName) {
        students = studentMapper.selectSimilarName(stuName);
        return students;
    }
}
