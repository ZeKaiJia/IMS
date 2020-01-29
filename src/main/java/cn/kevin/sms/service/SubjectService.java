package cn.kevin.sms.service;

import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.mapper.SubjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author kevin
 */
public class SubjectService {
    //这个是SQL会话，通过他可以发出SQL语句
    SqlSession sqlSession;


    private List<Subject> subjects;
    private SubjectMapper subjectMapper;
    private Subject subject;

    public SubjectService() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");

        //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言],通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        subjectMapper = sqlSession.getMapper(SubjectMapper.class);
    }

    protected void destroy() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Subject insert(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if ( subject == null ) {
            subjectMapper.insert(sub);
            destroy();
            return sub;
        }
        destroy();
        return null;
    }

    public Subject delete(Integer subId) {
        subject = subjectMapper.select(subId);
        if ( subject != null ) {
            subjectMapper.delete(subId);
        }
        destroy();
        return subject;
    }

    public Subject update(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if ( subject != null ) {
            subjectMapper.update(sub);
            destroy();
            return sub;
        }
        destroy();
        return null;
    }

    public Subject select(Integer subId) {
        subject = subjectMapper.select(subId);
        destroy();
        return subject;
    }

    public List<Subject> selectAll() {
        subjects = subjectMapper.selectAll();
        destroy();
        return subjects;
    }

    public List<Subject> selectByAllInfo(Subject subject) {
        subjects = subjectMapper.selectByAllInfo(subject);
        destroy();
        return subjects;
    }

    public List<Subject> selectSimilarName(String subName) {
        subjects = subjectMapper.selectSimilarName(subName);
        destroy();
        return subjects;
    }
}
