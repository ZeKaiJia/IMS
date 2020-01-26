package cn.kevin.sms.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author Sean Wu
 */
public abstract class SqlBiz<T> {

    //这个是SQL会话，通过他可以发出SQL语句
    SqlSession sqlSession;

    protected T mapper;


    private void destroy() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    private SqlBiz init(Class clazz) {
        try {
            //得到mybatis-config文件，转换成InputStream流对象
            InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言],通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过SqlSessionFactory对象获取一个SqlSession会话
            sqlSession = sqlSessionFactory.openSession();
            mapper = (T)sqlSession.getMapper(clazz);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    protected abstract void biz();


    public void call(Class clazz) {
        init(clazz);

        biz();

        destroy();
    }

}
