package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    void saveUserInfo(User sysUser);
    void deleteUser(String usrName);
    void disableUser(User sysUser);
    void recoverUser(User sysUser);
    void updateUserInfo(User sysUser);
    void userLogin(User sysUser);
    User selectByName(String usrName);
    List<User> selectAll();
    List<User> selectAnyParam(User sysUser);
}
