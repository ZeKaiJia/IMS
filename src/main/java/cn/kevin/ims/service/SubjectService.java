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

/**
 * The type Subject service.
 * SubjectService
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class SubjectService {
    /**
     * The Subject mapper.
     */
    @Resource
    private SubjectMapper subjectMapper;

    /**
     * The Subject.
     */
    private Subject subject;

    /**
     * Insert subject.
     * 插入
     * @param sub the sub
     * @return the subject
     */
    public Subject insert(Subject sub) {
        subject = subjectMapper.selectAdminById(sub.getSubId());
        if (subject == null) {
            sub.setUtcCreate(DateUtil.currentSecond());
            sub.setUtcModify(DateUtil.currentSecond());
            subjectMapper.insert(sub);
            return sub;
        }
        return null;
    }

    /**
     * Save subject.
     * 删除
     * @param subId the sub id
     * @return the subject
     */
    public Subject save(Integer subId) {
        subject = subjectMapper.select(subId);
        if (subject != null) {
            subjectMapper.save(subId);
        }
        return subject;
    }

    /**
     * Delete subject.
     * 禁用
     * @param subId the sub id
     * @return the subject
     */
    public Subject delete(Integer subId) {
        subject = subjectMapper.select(subId);
        if (subject != null) {
            subject.setUtcModify(DateUtil.currentSecond());
            subject.setIsReal(false);
            subjectMapper.delete(subject);
        }
        return subject;
    }

    /**
     * Re delete subject.
     * 恢复禁用
     * @param subId the sub id
     * @return the subject
     */
    public Subject reDelete(Integer subId) {
        List<Subject> subjects = subjectMapper.selectAdmin();
        for (Subject s : subjects) {
            if (s.getSubId().equals(subId)) {
                subject = s;
                break;
            }
        }
        if (subject != null) {
            subject.setUtcModify(DateUtil.currentSecond());
            subject.setIsReal(true);
            subjectMapper.reDelete(subject);
        }
        return subject;
    }

    /**
     * Update subject.
     * 更新
     * @param sub the sub
     * @return the subject
     */
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

    /**
     * Select subject.
     * 查找单个非禁用数据
     * @param subId the sub id
     * @return the subject
     */
    public Subject select(Integer subId) {
        return subjectMapper.select(subId);
    }

    /**
     * Select all list.
     * 查找所有禁用数据
     * @return the list
     */
    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }

    /**
     * Select by all info list.
     * 按任意字段查找非禁用数据
     * @param subject the subject
     * @return the list
     */
    public List<Subject> selectByAllInfo(Subject subject) {
        return subjectMapper.selectByAllInfo(subject);
    }

    /**
     * Select similar name list.
     * 按课程名字段查找非禁用数据
     * @param subName the sub name
     * @return the list
     */
    public List<Subject> selectSimilarName(String subName) {
        return subjectMapper.selectSimilarName(subName);
    }

    /**
     * Select garbage list.
     * 查找禁用数据
     * @return the list
     */
    public List<Subject> selectGarbage() {
        return subjectMapper.selectGarbage();
    }

    /**
     * Select admin list.
     * 查找所有数据
     * @return the list
     */
    public List<Subject> selectAdmin() {
        return subjectMapper.selectAdmin();
    }

    /**
     * Select admin by id subject.
     * 查找单个数据
     * @param subId the sub id
     * @return the subject
     */
    public Subject selectAdminById(Integer subId) {
        return subjectMapper.selectAdminById(subId);
    }
}
