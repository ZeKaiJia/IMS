package cn.kevin.sms.service;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kevin
 */
public class StudentService {
    //这个是SQL会话，通过他可以发出SQL语句
    SqlSession sqlSession;

    private Set<Student> studentSet = new HashSet<>();
    private StudentMapper studentMapper;
    private Student student;

    public StudentService() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言],通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    protected void destroy() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Student insert(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if ( student == null ) {
            studentMapper.insert(stu);
            destroy();
            return stu;
        }
        destroy();
        return null;
    }

    public Student delete(Integer stuId) {
        student = studentMapper.select(stuId);
        if ( student != null ) {
            studentMapper.deleteById(stuId);
        }
        destroy();
        return student;
    }

    public Student update(Student stu) {
        student = studentMapper.select(stu.getStuId());
        if (student != null) {
            studentMapper.update(stu);
            destroy();
            return stu;
        }
        destroy();
        return null;
    }

    public Student select(Integer stuId) {
        student = studentMapper.select(stuId);
        destroy();
        return student;
    }
}
