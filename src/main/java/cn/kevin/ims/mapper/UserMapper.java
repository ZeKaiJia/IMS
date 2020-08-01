package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kevin
 */
@Repository
public interface UserMapper {
    /**
     * 添加功能
     */
    void insert(User user);

    /**
     * 保存功能 删除数据
     */
    void save(String usrId);

    /**
     * 删除方法
     */
    void delete(User user);

    /**
     * re删除方法
     */
    void reDelete(User user);

    /**
     * 修改方法 修改密码
     */
    void update(User user);

    /**
     * 登录
     */
    void login(User user);

    /**
     * 查找方法
     */
    User select(String usrId);

    /**
     * 全体查找方法
     */
    List<User> selectAll();

    /**
     * 查找方法 多参数多返回 精确查找 or
     */
    List<User> selectByAllInfo(User user);

    /**
     * 管理员专用清除数据
     */
    List<User> selectGarbage();

    /**
     * 管理员专用搜索全体数据
     */
    List<User> selectAdmin();

    /**
     * 管理员专用搜索定向数据
     */
    User selectAdminById(String usrId);
}