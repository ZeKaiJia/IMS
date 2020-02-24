package cn.kevin.ims.service;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.mapper.UserMapper;
import cn.kevin.ims.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class UserService {
    @Resource
    private UserMapper userMapper;

    private User user;

    private List<User> users;

    public User insert(User usr) {
        user = userMapper.select(usr.getUsrId());
        if (user == null) {
            usr.setUtcCreate(DateUtils.currentSecond());
            usr.setUtcModify(DateUtils.currentSecond());
            userMapper.insert(usr);
            return usr;
        }
        return null;
    }


    public List<User> save() {
        users = userMapper.selectGarbage();
        if (users.size() != 0) {
            userMapper.save();
        }
        return users;
    }

    public User delete(String usrId) {
        user = userMapper.select(usrId);
        if (user != null) {
            user.setUtcModify(DateUtils.currentSecond());
            user.setIsReal(false);
            userMapper.delete(user);
        }
        return user;
    }

    public User update(User usr) {
        user = userMapper.select(usr.getUsrId());
        if (user != null) {
            user.setUtcModify(DateUtils.currentSecond());
            user.setUsrPassword(usr.getUsrPassword());
            userMapper.update(user);
            return user;
        }
        return null;
    }

    public User login(User usr) {
        users = userMapper.selectByAllInfo(usr);
        if (users.size() != 0) {
            user = select(usr.getUsrId());
            user.setLastLogin(DateUtils.currentSecond());
            userMapper.login(user);
            return user;
        }
        return null;
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
