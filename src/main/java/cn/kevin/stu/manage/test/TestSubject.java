package cn.kevin.stu.manage.test;

import cn.kevin.stu.manage.entity.Subject;
import cn.kevin.stu.manage.mapper.SubjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kevin
 */
public class TestSubject {
    //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言]
    private SqlSessionFactory sqlSessionFactory;
    //这个是SQL会话，通过他可以发出SQL语句
    private SqlSession sqlSession;

    private SubjectMapper subjectMapper;

    @Before
    public void init() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        subjectMapper = sqlSession.getMapper(SubjectMapper.class);
    }

    @Test
    public void testInsertSub() {
        for (int i=1; i<6; i++) {
            Subject subject = new Subject();
            subject.setSubId(i);
            subject.setSubName("高数重修" + i);
            subject.setSubTeacherId(i);
            subject.setSubCredit(i*2);
            subjectMapper.addSubject(subject);
        }
        System.out.println("Inserted!");
    }

    @Test
    public void testDeleteSub() {
        subjectMapper.deleteSubjectById(1);
        System.out.println("Deleted!");
    }

    @Test
    public void testUpdateStu() {
        Subject subject = new Subject();
        subject.setSubId(2);
        subject.setSubName("高数专升本");
        subject.setSubTeacherId(99);
        subject.setSubCredit(0);
        subjectMapper.updateSubject(subject);
        System.out.println("Updated!");
    }

    @Test
    public void testSelectStuId() {
        Subject subject;
        subject = subjectMapper.selectSubject(1);
        System.out.println(subject);
        System.out.println("Selected!");
    }

    @Test
    public void testSelectAllStu() {
        List<Subject> subjects;
        subjects = subjectMapper.selectAllSubject();
        for (Subject sub : subjects) {
            System.out.println(sub);
        }
        System.out.println("Selected!");
    }

    @After
    public void destory() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
