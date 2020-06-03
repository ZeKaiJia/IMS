package cn.kevin.ims.service;

import cn.kevin.ims.model.Student;
import cn.kevin.ims.dao.StudentMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    private Student student;

    public Student insert(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student == null) {
            stu.setUtcCreate(DateUtil.currentSecond());
            stu.setUtcModify(DateUtil.currentSecond());
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
            student.setUtcModify(DateUtil.currentSecond());
            student.setIsReal(false);
            studentMapper.delete(student);
        }
        return student;
    }

    public Student update(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student != null) {
            stu.setUtcCreate(student.getUtcCreate());
            stu.setUtcModify(DateUtil.currentSecond());
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
