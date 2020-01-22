package cn.kevin.sms.test;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.mapper.ScoreMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kevin
 */
public class ScoreTest {
    //这个是SQL会话，通过他可以发出SQL语句
    private SqlSession sqlSession;

    private ScoreMapper scoreMapper;

    List<Score> scores;

    Map<String, Object> map = new HashMap<>();

    @Before
    public void init() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言]
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        scoreMapper = sqlSession.getMapper(ScoreMapper.class);
    }

    @Test
    public void testInsert() {
        for (int i=1; i<11; i++) {
            Score score = new Score();
            score.setStuId(i);
            score.setSubId(11-i);
            score.setSubName("高数" + i);
            long time = System.currentTimeMillis();
            score.setSubScore(100 - i);
            score.setGmtCreate(time);
            score.setGmtModify(time);
            scoreMapper.insert(score);
        }
        System.out.println("Inserted!");
    }

    @Test
    public void testDelete() {
        map.put("stuId",1);
        map.put("subId",1);
        scoreMapper.delete(map);
        System.out.println("Deleted!");
    }

    @Test
    public void testDeleteAll() {
        scoreMapper.deleteAll(1);
        System.out.println("Deleted!");
    }

    @Test
    public void testUpdate() {
        Score score = new Score();
        score.setStuId(9);
        score.setSubId(1);
        score.setSubName("高数");
        long time = System.currentTimeMillis();
        score.setSubScore(60);
        score.setGmtCreate(time);
        score.setGmtModify(time);
        scoreMapper.update(score);
        System.out.println("Updated!");
    }

    @Test
    public void testSelect() {
        map.put("stuId",10);
        map.put("subId",1);
        scores = scoreMapper.select(map);
        System.out.println(scores);
        System.out.println("Selected!");
    }

    @Test
    public void testSelectByAllInfo() {
        Score score = new Score();
        score.setSubId(3);
        scores = scoreMapper.selectByAllInfo(score);
        for ( Score sco: scores ) {
            System.out.println(sco);
        }
        System.out.println("Selected!");
    }

    @Test
    public void testSelectPassScore() {
        scores = scoreMapper.selectPassScore(60);
        for ( Score sco : scores ) {
            System.out.println(sco);
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
