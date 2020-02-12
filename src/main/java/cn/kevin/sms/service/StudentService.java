package cn.kevin.sms.service;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.mapper.StudentMapper;
import cn.kevin.sms.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    private List<Student> students;

    private Student student;

    public Student insert(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if ( student == null ) {
            stu.setGmtCreate(DateUtils.currentSecond());
            stu.setGmtModify(DateUtils.currentSecond());
            studentMapper.insert(stu);
            return stu;
        }
        return null;
    }


    public List<Student> save() {
        students = studentMapper.selectGarbage();
        if ( students.size() != 0 ) {
            studentMapper.save();
        }
        return students;
    }

    public Student delete(Integer stuId) {
        student = studentMapper.select(stuId);
        if ( student != null ) {
            student.setGmtModify(DateUtils.currentSecond());
            student.setIsReal(false);
            studentMapper.delete(student);
        }
        return student;
    }

    public Student update(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student != null) {
            stu.setGmtCreate(student.getGmtCreate());
            stu.setGmtModify(DateUtils.currentSecond());
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
