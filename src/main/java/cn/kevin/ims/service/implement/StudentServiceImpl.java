package cn.kevin.ims.service.implement;

import cn.kevin.ims.entity.Student;
import cn.kevin.ims.mapper.StudentMapper;
import cn.kevin.ims.service.StudentService;
import cn.kevin.ims.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Student service.
 * @author kevin
 * 学生表Service层接口实现类
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    private Student student;
    @Override
    public Student saveStudentInfo(@NotNull Student paramStudent) {
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
    @Override
    public Student deleteStudent(String paramStudentId) {
        student = studentMapper.selectById(paramStudentId);
        if (student != null) {
            studentMapper.deleteStudent(paramStudentId);
            return student;
        } else {
            return null;
        }
    }
    @Override
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
    @Override
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
    @Override
    public Student updateStudentInfo(@NotNull Student paramStudent) {
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
    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
    @Override
    public List<Student> selectAnyParam(Student paramStudent) {
        return studentMapper.selectAnyParam(paramStudent);
    }
    @Override
    public Student selectById(String paramStudentId) {
        return studentMapper.selectById(paramStudentId);
    }
}
