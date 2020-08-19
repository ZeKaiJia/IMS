package cn.kevin.ims.service.implement;

import cn.kevin.ims.entity.Subject;
import cn.kevin.ims.mapper.SubjectMapper;
import cn.kevin.ims.service.SubjectService;
import cn.kevin.ims.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Subject service.
 * 课程表Service层接口实现类
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class SubjectServiceImpl implements SubjectService {
    @Resource
    private SubjectMapper subjectMapper;
    private Subject subject;
    @Override
    public Subject saveSubjectInfo(@NotNull Subject paramSubject) {
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
    @Override
    public Subject deleteSubject(Integer paramSubjectId) {
        subject = subjectMapper.selectById(paramSubjectId);
        if (subject != null) {
            subjectMapper.deleteSubject(paramSubjectId);
            return subject;
        } else {
            return null;
        }
    }
    @Override
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
    @Override
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
    @Override
    public Subject updateSubjectInfo(@NotNull Subject paramSubject) {
        subject = subjectMapper.selectById(paramSubject.getSubId());
        if (subject != null) {
            paramSubject.setUtcModify(DateUtil.currentSecond());
            subjectMapper.updateSubjectInfo(paramSubject);
            return paramSubject;
        } else {
            return null;
        }
    }
    @Override
    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }
    @Override
    public Subject selectById(Integer paramSubjectId) {
        return subjectMapper.selectById(paramSubjectId);
    }
    @Override
    public List<Subject> selectAnyParam(Subject paramSubject) {
        return subjectMapper.selectAnyParam(paramSubject);
    }
}
