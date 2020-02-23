package cn.kevin.ims.service;

import cn.kevin.ims.entity.Student;
import cn.kevin.ims.mapper.StudentMapper;
import cn.kevin.ims.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    private Student student;

    public Student insert(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student == null) {
            stu.setUtcCreate(DateUtils.currentSecond());
            stu.setUtcModify(DateUtils.currentSecond());
            studentMapper.insert(stu);
            return stu;
        }
        return null;
    }


    public List<Student> save() {
        List<Student> students = studentMapper.selectGarbage();
        if (students.size() != 0) {
            studentMapper.save();
        }
        return students;
    }

    public Student delete(Integer stuId) {
        student = studentMapper.select(stuId);
        if (student != null) {
            student.setUtcModify(DateUtils.currentSecond());
            student.setIsReal(false);
            studentMapper.delete(student);
        }
        return student;
    }

    public Student update(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student != null) {
            stu.setUtcCreate(student.getUtcCreate());
            stu.setUtcModify(DateUtils.currentSecond());
            studentMapper.update(stu);
            return stu;
        }
        return null;
    }

    public Student select(Integer stuId) {
        return studentMapper.select(stuId);
    }

    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    public List<Student> selectByAllInfo(Student student) {
        return studentMapper.selectByAllInfo(student);
    }

    public List<Student> selectSimilarName(String stuName) {
        return studentMapper.selectSimilarName(stuName);
    }

    public List<Student> selectGarbage() {
        return studentMapper.selectGarbage();
    }
}
