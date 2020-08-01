package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Subject mapper.
 * SubjectMapper
 * @author kevin
 */
@Repository
public interface SubjectMapper {
    /**
     * 添加方法
     *
     * @param subject the subject
     */
    void insert(Subject subject);

    /**
     * 保存功能 删除不存在数据
     *
     * @param subId the sub id
     */
    void save(Integer subId);

    /**
     * 删除方法 id
     *
     * @param subject the subject
     */
    void delete(Subject subject);

    /**
     * re删除方法
     *
     * @param subject the subject
     */
    void reDelete(Subject subject);

    /**
     * 修改方法
     *
     * @param subject the subject
     */
    void update(Subject subject);

    /**
     * 单个查找方法 id
     *
     * @param subId the sub id
     * @return the subject
     */
    Subject select(Integer subId);

    /**
     * 全体查找方法
     *
     * @return the list
     */
    List<Subject> selectAll();

    /**
     * 多参数多返回查找方法
     *
     * @param subject the subject
     * @return the list
     */
    List<Subject> selectByAllInfo(Subject subject);

    /**
     * 模糊查找
     *
     * @param subName the sub name
     * @return the list
     */
    List<Subject> selectSimilarName(String subName);

    /**
     * 管理员专用
     *
     * @return the list
     */
    List<Subject> selectGarbage();


    /**
     * 管理员专用搜索全体数据
     *
     * @return the list
     */
    List<Subject> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     *
     * @param subId the sub id
     * @return the subject
     */
    Subject selectAdminById(Integer subId);
}
