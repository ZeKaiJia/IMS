package cn.kevin.sms.test;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author kevin
 */
public class StudentTest {
    //这个是SQL会话，通过他可以发出SQL语句
    private SqlSession sqlSession;

    private StudentMapper studentMapper;

    List<Student> students;

    @Before
    public void init() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("");

        //通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言]
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setStuId(100);
        student.setStuAge(10);

        String strDate = "1999-10-3";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setStuBirthday(date);
        student.setStuEmail("84592305@qq.com");
        student.setStuGender(1);
        student.setStuName("贾泽楷");
        studentMapper.insert(student);
        System.out.println("Inserted!");
    }

    @Test
    public void testDelete() {
        studentMapper.delete(1);
        System.out.println("Deleted!");
    }

    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setStuId(100);
        student.setStuAge(99);
        student.setStuBirthday(new Date());
        student.setStuEmail("999999999@qq.com");
        student.setStuGender(3);
        student.setStuName("9贾9泽9楷9");
        studentMapper.update(student);
        System.out.println("Updated!");
    }

    @Test
    public void testSelect() {
        Student student;
        student = studentMapper.select(1);
        System.out.println(student);
        System.out.println("Selected!");
    }

    @Test
    public void testSelectAll() {
        students = studentMapper.selectAll();
        for (Student stu : students) {
            System.out.println(stu);
        }
        System.out.println("Selected!");
    }

    @Test
    public void testSelectByAllInfo() {
        Student student = new Student();
        student.setStuGender(1);
        students = studentMapper.selectByAllInfo(student);
        for (Student stu : students) {
            System.out.println(stu);
        }
        System.out.println("Selected!");
    }

    @Test
    public void testSelectSimilarName() {
        students = studentMapper.selectSimilarName("9");
        for (Student stu : students) {
            System.out.println(stu);
        }
        System.out.println("Selected!");
    }

    @After
    public void destroy() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
