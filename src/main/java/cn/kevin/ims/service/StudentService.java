package cn.kevin.ims.service;

import cn.kevin.ims.entity.Student;
import cn.kevin.ims.mapper.StudentMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
    private Student student;
    public Student saveStudentInfo(Student paramStudent) {
        student = studentMapper.selectById(paramStudent.getStuId());
        if (student == null) {
            paramStudent.setUtcCreate(DateUtil.currentSecond());
            paramStudent.setUtcModify(DateUtil.currentSecond());
            studentMapper.saveStudentInfo(paramStudent);
            return paramStudent;
        } else {
            return null;
        }
    }
    public Student deleteStudent(String paramStudentId) {
        student = studentMapper.selectById(paramStudentId);
        if (student != null) {
            studentMapper.deleteStudent(paramStudentId);
            return student;
        } else {
            return null;
        }
    }
    public Student disableStudent(String paramStudentId) {
        student = studentMapper.selectById(paramStudentId);
        if (student != null) {
            student.setUtcModify(DateUtil.currentSecond());
            student.setValid(false);
            studentMapper.disableStudent(student);
            return student;
        } else {
            return null;
        }
    }
    public Student recoverStudent(String paramStudentId) {
        student = studentMapper.selectById(paramStudentId);
        if (student != null) {
            student.setUtcModify(DateUtil.currentSecond());
            student.setValid(true);
            studentMapper.recoverStudent(student);
            return student;
        } else {
            return null;
        }
    }
    public Student updateStudentInfo(Student paramStudent) {
        student = studentMapper.selectById(paramStudent.getStuId());
        if (student != null) {
            paramStudent.setUtcModify(DateUtil.currentSecond());
            paramStudent.setUtcCreate(student.getUtcCreate());
            studentMapper.updateStudentInfo(paramStudent);
            return paramStudent;
        } else {
            return null;
        }
    }
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
    public List<Student> selectAnyParam(Student paramStudent) {
        return studentMapper.selectAnyParam(paramStudent);
    }
    public Student selectById(String paramStudentId) {
        return studentMapper.selectById(paramStudentId);
    }
}
