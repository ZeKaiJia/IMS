package cn.kevin.ims.service;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.mapper.SubjectMapper;
import cn.kevin.ims.util.DateUtils;
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
            sub.setGmtCreate(DateUtils.currentSecond());
            sub.setGmtModify(DateUtils.currentSecond());
            subjectMapper.insert(sub);
            return sub;
        }
        return null;
    }

    public List<Subject> save() {
        subjects = subjectMapper.selectGarbage();
        if ( subjects.size() != 0 ) {
            subjectMapper.save();
        }
        return subjects;
    }

    public Subject delete(Integer subId) {
        subject = subjectMapper.select(subId);
        if ( subject != null ) {
            subject.setGmtModify(DateUtils.currentSecond());
            subject.setIsReal(false);
            subjectMapper.delete(subject);
        }
        return subject;
    }

    public Subject update(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if ( subject != null ) {
            sub.setGmtCreate(subject.getGmtCreate());
            sub.setGmtModify(DateUtils.currentSecond());
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
