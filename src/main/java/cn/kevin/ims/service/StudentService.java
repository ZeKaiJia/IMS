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

/**
 * The type Student service.
 * StudentService
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class StudentService {
    /**
     * The Student mapper.
     */
    @Resource
    private StudentMapper studentMapper;

    /**
     * The Student.
     */
    private Student student;

    /**
     * Insert student.
     * 插入
     * @param stu the stu
     * @return the student
     */
    public Student insert(Student stu) {
        student = studentMapper.selectAdminById(stu.getStuId());
        if (student == null) {
            stu.setUtcCreate(DateUtil.currentSecond());
            stu.setUtcModify(DateUtil.currentSecond());
            studentMapper.insert(stu);
            return stu;
        }
        return null;
    }


    /**
     * Save student.
     * 删除
     * @param stuId the stu id
     * @return the student
     */
    public Student save(Integer stuId) {
        student = studentMapper.select(stuId);
        if (student != null) {
            studentMapper.save(stuId);
        }
        return student;
    }

    /**
     * Delete student.
     * 禁用
     * @param stuId the stu id
     * @return the student
     */
    public Student delete(Integer stuId) {
        student = studentMapper.select(stuId);
        if (student != null) {
            student.setUtcModify(DateUtil.currentSecond());
            student.setValid(false);
            studentMapper.delete(student);
        }
        return student;
    }

    /**
     * Re delete student.
     * 回复禁用
     * @param stuId the stu id
     * @return the student
     */
    public Student reDelete(Integer stuId) {
        List<Student> students = studentMapper.selectAdmin();
        for (Student s : students) {
            if (s.getStuId().equals(stuId)) {
                student = s;
                break;
            }
        }
        if (student != null) {
            student.setUtcModify(DateUtil.currentSecond());
            student.setValid(true);
            studentMapper.reDelete(student);
        }
        return student;
    }

    /**
     * Update student.
     * 更新
     * @param stu the stu
     * @return the student
     */
    public Student update(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student != null) {
            stu.setUtcModify(DateUtil.currentSecond());
            stu.setUtcCreate(student.getUtcCreate());
            studentMapper.update(stu);
            return stu;
        }
        return null;
    }

    /**
     * Select student.
     * 查找单个非禁用数据
     * @param stuId the stu id
     * @return the student
     */
    public Student select(Integer stuId) {
        return studentMapper.select(stuId);
    }

    /**
     * Select all list.
     * 查找所有非禁用数据
     * @return the list
     */
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    /**
     * Select by all info list.
     * 按任意字段查找非禁用数据
     * @param student the student
     * @return the list
     */
    public List<Student> selectByAllInfo(Student student) {
        return studentMapper.selectByAllInfo(student);
    }

    /**
     * Select similar name list.
     * 按姓名字段查找非禁用数据
     * @param stuName the stu name
     * @return the list
     */
    public List<Student> selectSimilarName(String stuName) {
        return studentMapper.selectSimilarName(stuName);
    }

    /**
     * Select garbage list.
     * 查找禁用数据
     * @return the list
     */
    public List<Student> selectGarbage() {
        return studentMapper.selectGarbage();
    }

    /**
     * Select admin list.
     * 查找所有数据
     * @return the list
     */
    public List<Student> selectAdmin() {
        return studentMapper.selectAdmin();
    }

    /**
     * Select admin by id student.
     * 查找单个数据
     * @param stuId the stu id
     * @return the student
     */
    public Student selectAdminById(Integer stuId) {
        return studentMapper.selectAdminById(stuId);
    }
}
