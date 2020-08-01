package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface User mapper.
 * UserMapper
 * @author kevin
 */
@Repository
public interface UserMapper {
    /**
     * 添加功能
     *
     * @param user the user
     */
    void insert(User user);

    /**
     * 保存功能 删除数据
     *
     * @param usrId the usr id
     */
    void save(String usrId);

    /**
     * 删除方法
     *
     * @param user the user
     */
    void delete(User user);

    /**
     * re删除方法
     *
     * @param user the user
     */
    void reDelete(User user);

    /**
     * 修改方法 修改密码
     *
     * @param user the user
     */
    void update(User user);

    /**
     * 登录
     *
     * @param user the user
     */
    void login(User user);

    /**
     * 查找方法
     *
     * @param usrId the usr id
     * @return the user
     */
    User select(String usrId);

    /**
     * 全体查找方法
     *
     * @return the list
     */
    List<User> selectAll();

    /**
     * 查找方法 多参数多返回 精确查找 or
     *
     * @param user the user
     * @return the list
     */
    List<User> selectByAllInfo(User user);

    /**
     * 管理员专用清除数据
     *
     * @return the list
     */
    List<User> selectGarbage();

    /**
     * 管理员专用搜索全体数据
     *
     * @return the list
     */
    List<User> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     *
     * @param usrId the usr id
     * @return the user
     */
    User selectAdminById(String usrId);
}
