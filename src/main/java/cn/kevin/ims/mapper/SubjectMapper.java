package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {
    void saveSubjectInfo(Subject subject);
    void deleteSubject(Integer subId);
    void disableSubject(Subject subject);
    void recoverSubject(Subject subject);
    void updateSubjectInfo(Subject subject);
    List<Subject> selectAll();
    List<Subject> selectAnyParam(Subject subject);
    Subject selectById(Integer subId);
}
