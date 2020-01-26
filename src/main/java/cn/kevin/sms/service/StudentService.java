package cn.kevin.sms.service;

import cn.kevin.sms.SqlCall;
import cn.kevin.sms.entity.Student;
import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.mapper.StudentMapper;
import cn.kevin.sms.util.SqlBiz;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author kevin
 */
@Service
public class StudentService {



    @Resource
    private StudentMapper studentMapper ;

    private List<Student> students;

    private Student student;
    private Student temp;

    public Student insert(Student stu) {
        Student student = studentMapper.select(stu.getStuId());
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
            temp = new Student(stu);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(temp.getStuBirthday());
            calendar.add(Calendar.DATE,1);
            temp.setStuBirthday(calendar.getTime());
            studentMapper.update(temp);

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
