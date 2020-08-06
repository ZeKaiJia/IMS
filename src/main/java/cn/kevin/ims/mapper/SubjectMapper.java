package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {
    void saveSubjectInfo(Subject subject);
    void deleteSubject(@Param("subId") Integer subId);
    void disableSubject(Subject subject);
    void recoverSubject(Subject subject);
    void updateSubjectInfo(Subject subject);
    List<Subject> selectAll();
    List<Subject> selectAnyParam(Subject subject);
    Subject selectById(@Param("subId") Integer subId);
}
