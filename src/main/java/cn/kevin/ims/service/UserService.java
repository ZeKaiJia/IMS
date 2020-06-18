package cn.kevin.ims.service;

import cn.kevin.ims.model.User;
import cn.kevin.ims.dao.UserMapper;
import cn.kevin.ims.util.DateUtil;
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
            usr.setUtcCreate(DateUtil.currentSecond());
            usr.setUtcModify(DateUtil.currentSecond());
            userMapper.insert(usr);
            return usr;
        }
        return null;
    }


    public User save(String usrId) {
        user = userMapper.selectAdminById(usrId);
        if (user != null) {
            userMapper.save(usrId);
        }
        return user;
    }

    public User delete(String usrId) {
        user = userMapper.select(usrId);
        if (user != null) {
            user.setUtcModify(DateUtil.currentSecond());
            user.setIsReal(false);
            userMapper.delete(user);
        }
        return user;
    }

    public User redelete(String usrId) {
        users = userMapper.selectAdmin();
        for (User u : users) {
            if (u.getUsrId().equals(usrId)) {
                user = u;
                break;
            }
        }
        if (user != null) {
            user.setUtcModify(DateUtil.currentSecond());
            user.setIsReal(true);
            userMapper.redelete(user);
        }
        return user;
    }

    public User update(User usr) {
        user = userMapper.select(usr.getUsrId());
        if (user != null) {
            user.setUtcModify(DateUtil.currentSecond());
            user.setUsrPassword(usr.getUsrPassword());
            user.setUsrType(usr.getUsrType());
            userMapper.update(user);
            return user;
        }
        return null;
    }

    public User login(String usrId, String usrPassword) {
        user = userMapper.select(usrId);
        if (user != null ) {
            if (user.getUsrPassword().equals(usrPassword)) {
                user.setLastLogin(DateUtil.currentSecond());
                userMapper.login(user);
            }
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

    public User selectAdminById(String usrId) {
        return userMapper.selectAdminById(usrId);
    }

    public List<User> selectGarbage() {
        return userMapper.selectGarbage();
    }

    public List<User> selectAdmin() {
        return userMapper.selectAdmin();
    }
}
