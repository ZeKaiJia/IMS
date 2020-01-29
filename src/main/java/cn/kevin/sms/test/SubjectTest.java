package cn.kevin.sms.test;

import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.mapper.SubjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author kevin
 */
public class SubjectTest {
    //这个是SQL会话，通过他可以发出SQL语句
    private SqlSession sqlSession;

    private SubjectMapper subjectMapper;

    List<Subject> subjects;

    @Before
    public void init() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");

        //通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言]
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        subjectMapper = sqlSession.getMapper(SubjectMapper.class);
    }

    @Test
    public void testInsert() {
        for (int i=1; i<6; i++) {
            Subject subject = new Subject();
            subject.setSubId(i);
            subject.setSubName("高数重修" + i);
            subject.setSubTeacherId(i);
            subject.setSubCredit(i*2);
            subjectMapper.insert(subject);
        }
        System.out.println("Inserted!");
    }

    @Test
    public void testDelete() {
        subjectMapper.delete(1);
        System.out.println("Deleted!");
    }

    @Test
    public void testUpdate() {
        Subject subject = new Subject();
        subject.setSubId(2);
        subject.setSubName("高数专升本");
        subject.setSubTeacherId(99);
        subject.setSubCredit(0);
        subjectMapper.update(subject);
        System.out.println("Updated!");
    }

    @Test
    public void testSelect() {
        Subject subject;
        subject = subjectMapper.select(1);
        System.out.println(subject);
        System.out.println("Selected!");
    }

    @Test
    public void testSelectAll() {
        subjects = subjectMapper.selectAll();
        for (Subject sub : subjects) {
            System.out.println(sub);
        }
        System.out.println("Selected!");
    }

    @Test
    public void testSelectByAllInfo() {
        Subject subject = new Subject();
        subject.setSubTeacherId(3);
        subjects = subjectMapper.selectByAllInfo(subject);
        for (Subject sub : subjects) {
            System.out.println(sub);
        }
        System.out.println("Selected!");
    }

    @Test
    public void testSelectSimilarName() {
        subjects = subjectMapper.selectSimilarName("重修");
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
