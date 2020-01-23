package cn.kevin.sms.service;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.mapper.ScoreMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.*;

/**
 * @author kevin
 */
public class ScoreService {
    //这个是SQL会话，通过他可以发出SQL语句
    SqlSession sqlSession;

    private List<Score> scores;
    private ScoreMapper scoreMapper;
    private Score score;
    private Map<String, Object> map;

    public ScoreService() throws Exception {
        //得到mybatis-config文件，转换成InputStream流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //这是个SQL会话工厂对象[表示通过会话发出SQL原生语言],通过SqlSessionFactoryBuilder得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory对象获取一个SqlSession会话
        sqlSession = sqlSessionFactory.openSession();

        scoreMapper = sqlSession.getMapper(ScoreMapper.class);
    }

    protected void destroy() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Score insert(Score sco) {
        map = new HashMap<>(4);
        map.put("stuId",sco.getStuId());
        map.put("subId",sco.getSubId());
        score = scoreMapper.select(map);
        if ( score == null ) {
            scoreMapper.insert(sco);
            destroy();
            return sco;
        }
        destroy();
        return null;
    }

    public Score delete(Integer stuId, Integer subId) {
        map = new HashMap<>(4);
        map.put("stuId",stuId);
        map.put("subId",subId);
        score = scoreMapper.select(map);
        if ( score != null ) {
            scoreMapper.delete(map);
        }
        destroy();
        return score;
    }

    public List<Score> deleteAll(Integer stuId) {
        score = new Score();
        score.setStuId(stuId);
        scores = scoreMapper.selectByAllInfo(score);
        if ( scores.size() != 0 ) {
            scoreMapper.deleteAll(stuId);
        }
        destroy();
        return scores;
    }

    public Score update(Score sco) {
        map = new HashMap<>(4);
        map.put("stuId",score.getStuId());
        map.put("subId",score.getSubId());
        score = scoreMapper.select(map);
        if (score != null) {
            scoreMapper.update(sco);
            destroy();
            return sco;
        }
        destroy();
        return null;
    }

    public Score select(Integer stuId, Integer subId) {
        map = new HashMap<>();
        map.put("stuId",stuId);
        map.put("subId",subId);
        score = scoreMapper.select(map);
        destroy();
        return score;
    }

    public List<Score> selectAll() {
        scores = scoreMapper.selectAll();
        destroy();
        return scores;
    }

    public List<Score> selectByAllInfo(Score sco) {
        List<Score> scores = scoreMapper.selectByAllInfo(sco);
        destroy();
        return scores;
    }

    public List<Score> selectPassScore(Integer subScore) {
        scores = scoreMapper.selectPassScore(subScore);
        destroy();
        return scores;
    }
}
