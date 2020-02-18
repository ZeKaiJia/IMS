package cn.kevin.ims.service;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.mapper.UserMapper;
import cn.kevin.ims.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    private List<User> users;

    private User user;

    public User insert(User usr) {
        user = userMapper.select(usr.getUsrId());
        if ( user == null ) {
            usr.setUtcCreate(DateUtils.currentSecond());
            usr.setUtcModify(DateUtils.currentSecond());
            userMapper.insert(usr);
            return usr;
        }
        return null;
    }


    public List<User> save() {
        users = userMapper.selectGarbage();
        if ( users.size() != 0 ) {
            userMapper.save();
        }
        return users;
    }

    public User delete(String usrId) {
        user = userMapper.select(usrId);
        if ( user != null ) {
            user.setUtcModify(DateUtils.currentSecond());
            user.setIsReal(false);
            userMapper.delete(user.getUsrId());
        }
        return user;
    }

    public User update(User usr) {
        user = userMapper.select(usr.getUsrId());
        if (user != null) {
            usr.setUtcCreate(user.getUtcCreate());
            usr.setUtcModify(DateUtils.currentSecond());
            userMapper.update(usr);
            return usr;
        }
        return null;
    }

    public void login(User usr) {
        user = userMapper.select(usr.getUsrId());
        if (user != null) {
            user.setLastLogin(DateUtils.currentSecond());
            userMapper.login(user);
        }
    }

    public User select(String usrId) {
        return userMapper.select(usrId);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public List<User> selectByAllInfo(User user) {
        return userMapper.selectByAllInfo(user);
    }

    public List<User> selectGarbage() {
        return userMapper.selectGarbage();
    }
}
