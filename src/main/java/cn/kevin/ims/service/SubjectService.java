package cn.kevin.ims.service;

import cn.kevin.ims.entity.Subject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The interface Subject service.
 * 课程表Service层接口类
 * @Author: Kevin
 * @Date: 2020 /8/19 3:39 下午
 */
public interface SubjectService {
    /**
     * Save subject info subject.
     * 根据课程对象插入课程数据
     * @param paramSubject the param subject
     * @return the subject
     */
    Subject saveSubjectInfo(@NotNull Subject paramSubject);

    /**
     * Delete subject subject.
     * 根据课程号删除课程
     * @param paramSubjectId the param subject id
     * @return the subject
     */
    Subject deleteSubject(Integer paramSubjectId);

    /**
     * Disable subject subject.
     * 根据课程号禁用课程
     * @param paramSubjectId the param subject id
     * @return the subject
     */
    Subject disableSubject(Integer paramSubjectId);

    /**
     * Recover subject subject.
     * 根据课程号恢复课程
     * @param paramSubjectId the param subject id
     * @return the subject
     */
    Subject recoverSubject(Integer paramSubjectId);

    /**
     * Update subject info subject.
     * 根据课程对象更新课程
     * @param paramSubject the param subject
     * @return the subject
     */
    Subject updateSubjectInfo(@NotNull Subject paramSubject);

    /**
     * Select all list.
     * 查询课程列表
     * @return the list
     */
    List<Subject> selectAll();

    /**
     * Select by id subject.
     * 根据课程号查询课程
     * @param paramSubjectId the param subject id
     * @return the subject
     */
    Subject selectById(Integer paramSubjectId);

    /**
     * Select any param list.
     * 根据任意字段查询课程
     * @param paramSubject the param subject
     * @return the list
     */
    List<Subject> selectAnyParam(Subject paramSubject);
}
