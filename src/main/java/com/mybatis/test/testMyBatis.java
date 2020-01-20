package com.mybatis.test;
import com.mybatis.entity.Monster;
import com.mybatis.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import java.io.InputStream;
import java.util.Date;

public class testMyBatis {

    //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言]
    private SqlSessionFactory sqlSessionFactory;
    //这个是SQL会话，通过他可以发出SQL语句
    private SqlSession sqlSession;

    @Before
    public void init() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        sqlSessionFactory = new SqlSessionFactoryBuilder(). build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test01() {
        //通过SqlSession会话对象获取到一个接口对象
        MonsterMapper monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        Monster monster = new Monster();
        monster.setAge(100);
        monster.setBirthday(new Date());
        monster.setEmail("845923059@qq.com");
        monster.setGender(1);
        monster.setName("贾泽楷");
        monster.setSalary(12000.25);

        monsterMapper.addMonster(monster);

        System.out.println("Saved!");
    }

    //完成提交的任务放在after注解的方法后
    @After
    public void destory() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
