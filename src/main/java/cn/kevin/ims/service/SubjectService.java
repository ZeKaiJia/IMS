package cn.kevin.ims.service;

import cn.kevin.ims.model.Subject;
import cn.kevin.ims.dao.SubjectMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;

    private Subject subject;

    public Subject insert(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if (subject == null) {
            sub.setUtcCreate(DateUtil.currentSecond());
            sub.setUtcModify(DateUtil.currentSecond());
            subjectMapper.insert(sub);
            return sub;
        }
        return null;
    }

    public List<Subject> save() {
        List<Subject> subjects = subjectMapper.selectGarbage();
        if (subjects.size() != 0) {
            subjectMapper.save();
        }
        return subjects;
    }

    public Subject delete(Integer subId) {
        subject = subjectMapper.select(subId);
        if (subject != null) {
            subject.setUtcModify(DateUtil.currentSecond());
            subject.setIsReal(false);
            subjectMapper.delete(subject);
        }
        return subject;
    }

    public Subject update(Subject sub) {
        subject = subjectMapper.select(sub.getSubId());
        if (subject != null) {
            sub.setUtcCreate(subject.getUtcCreate());
            sub.setUtcModify(DateUtil.currentSecond());
            subjectMapper.update(sub);
            return sub;
        }
        return null;
    }

    public Subject select(Integer subId) {
        return subjectMapper.select(subId);
    }

    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }

    public List<Subject> selectByAllInfo(Subject subject) {
        return subjectMapper.selectByAllInfo(subject);
    }

    public List<Subject> selectSimilarName(String subName) {
        return subjectMapper.selectSimilarName(subName);
    }

    public List<Subject> selectGarbage() {
        return subjectMapper.selectGarbage();
    }
}
