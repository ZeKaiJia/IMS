package cn.kevin.stu.manage.test;

import cn.kevin.stu.manage.entity.Student;
import cn.kevin.stu.manage.entity.Subject;
import cn.kevin.stu.manage.mapper.StudentMapper;
import cn.kevin.stu.manage.mapper.SubjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;

/**
 * @author kevin
 */
public class TestStudent {
    //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言]
    private SqlSessionFactory sqlSessionFactory;
    //这个是SQL会话，通过他可以发出SQL语句
    private SqlSession sqlSession;

    private StudentMapper studentMapper;
    private SubjectMapper subjectMapper;

    @Before
    public void init() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        studentMapper = sqlSession.getMapper(StudentMapper.class);
        subjectMapper = sqlSession.getMapper(SubjectMapper.class);
    }

    @Test
    public void testInsertStu() {
        for (int i=1; i<11; i++) {
            Student student = new Student();
            student.setStuId(i);
            student.setStuAge(10+i);
            student.setStuBirthday(new Date());
            student.setStuEmail("84592305" + i + "@qq.com");
            student.setStuGender(i%2);
            student.setStuName("贾泽楷" + i);
            studentMapper.addStudent(student);
        }
        System.out.println("Inserted!");
    }

    @Test
    public void testDeleteStu() {
        studentMapper.deleteStudentById(1);
        System.out.println("Deleted!");
    }

    @Test
    public void testUpdateStu() {
        Student student = new Student();
        student.setStuId(1);
        student.setStuAge(99);
        student.setStuBirthday(new Date());
        student.setStuEmail("999999999@qq.com");
        student.setStuGender(3);
        student.setStuName("9贾9泽9楷9");
        studentMapper.addStudent(student);
        System.out.println("Updated!");
    }

    @Test
    public void testInsertSub() {
        for (int i=1; i<6; i++) {
            Subject subject = new Subject();
            subject.setSubId(i);
            subject.setSubName("0" + i);
            subject.setSubTeacherId(i);
            subject.setSubCredit(i*2);
            subjectMapper.addSubject(subject);
        }
        System.out.println("Inserted!");
    }

    @After
    public void destory() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
