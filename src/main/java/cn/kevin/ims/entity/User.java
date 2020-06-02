package cn.kevin.ims.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: Kevin
 * @Date: 2020/2/18 10:56 下午
 */
@Getter
@Setter
@ToString
public class User extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private String usrId;
    private String usrPassword;
    private String usrType;
    private long lastLogin;

    public User() {
        super();
    }

    public User(String usrId, String usrPassword, String usrType, long lastLogin, long utcCreate, long utcModify) {
        super(utcCreate, utcModify);
        this.usrId = usrId;
        this.usrPassword = usrPassword;
        this.usrType = usrType;
        this.lastLogin = lastLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.getIsReal().equals(user.getIsReal()) &&
                this.usrId.equals(user.usrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.usrId, this.getIsReal());
    }
}
