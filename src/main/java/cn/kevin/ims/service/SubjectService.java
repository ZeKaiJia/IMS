package cn.kevin.ims.service;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.mapper.SubjectMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;
    private Subject subject;
    public Subject saveSubjectInfo(Subject paramSubject) {
        subject = subjectMapper.selectById(paramSubject.getSubId());
        if (subject == null) {
            paramSubject.setUtcCreate(DateUtil.currentSecond());
            paramSubject.setUtcModify(DateUtil.currentSecond());
            subjectMapper.saveSubjectInfo(paramSubject);
            return paramSubject;
        } else {
            return null;
        }
    }
    public Subject deleteSubject(Integer paramSubjectId) {
        subject = subjectMapper.selectById(paramSubjectId);
        if (subject != null) {
            subjectMapper.deleteSubject(paramSubjectId);
            return subject;
        } else {
            return null;
        }
    }
    public Subject disableSubject(Integer paramSubjectId) {
        subject = subjectMapper.selectById(paramSubjectId);
        if (subject != null) {
            subject.setUtcModify(DateUtil.currentSecond());
            subject.setValid(false);
            subjectMapper.disableSubject(subject);
            return subject;
        } else {
            return null;
        }
    }
    public Subject recoverSubject(Integer paramSubjectId) {
        subject = subjectMapper.selectById(paramSubjectId);
        if (subject != null) {
            subject.setUtcModify(DateUtil.currentSecond());
            subject.setValid(true);
            subjectMapper.recoverSubject(subject);
            return subject;
        } else {
            return null;
        }
    }
    public Subject updateSubjectInfo(Subject paramSubject) {
        subject = subjectMapper.selectById(paramSubject.getSubId());
        if (subject != null) {
            paramSubject.setUtcModify(DateUtil.currentSecond());
            subjectMapper.updateSubjectInfo(paramSubject);
            return paramSubject;
        } else {
            return null;
        }
    }
    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }
    public Subject selectById(Integer paramSubjectId) {
        return subjectMapper.selectById(paramSubjectId);
    }
    public List<Subject> selectAnyParam(Subject paramSubject) {
        return subjectMapper.selectAnyParam(paramSubject);
    }
}
