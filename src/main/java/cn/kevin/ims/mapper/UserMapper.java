package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kevin
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 添加功能
     */
    void insert(User user);

    /**
     * 保存功能 删除不存在数据
     */
    void save();

    /**
     * 删除方法
     */
    void delete(String usrId);

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
     * 管理员专用
     */
    List<User> selectGarbage();
}
