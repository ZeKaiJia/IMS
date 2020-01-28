package cn.kevin.sms.service;

import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;

    private List<Subject> subjects;

    private Subject subject;

    public Subject insert(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if ( subject == null ) {
            subjectMapper.insert(sub);
            return sub;
        }
        return null;
    }

    public Subject delete(Integer subId) {
        subject = subjectMapper.select(subId);
        if ( subject != null ) {
            subjectMapper.delete(subId);
        }
        return subject;
    }

    public Subject update(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if ( subject != null ) {
            subjectMapper.update(sub);
            return sub;
        }
        return null;
    }

    public Subject select(Integer subId) {
        subject = subjectMapper.select(subId);
        return subject;
    }

    public List<Subject> selectAll() {
        subjects = subjectMapper.selectAll();
        return subjects;
    }

    public List<Subject> selectByAllInfo(Subject subject) {
        subjects = subjectMapper.selectByAllInfo(subject);
        return subjects;
    }

    public List<Subject> selectSimilarName(String subName) {
        subjects = subjectMapper.selectSimilarName(subName);
        return subjects;
    }
}
