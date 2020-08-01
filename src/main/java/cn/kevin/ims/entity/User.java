package cn.kevin.ims.entity;

import lombok.*;

import java.util.Objects;

/**
 * 用户实体类
 * User
 * @Author: Kevin
 * @Date: 2020 /2/18 10:56 下午
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {
    /**
     * 序列化参数
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String usrId;
    /**
     * 用户密码
     */
    private String usrPassword;
    /**
     * 用户类型
     */
    private String usrType;
    /**
     * 最近登录时间
     */
    private long lastLogin;

    /**
     * Equals boolean.
     * 判断类型
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.usrId.equals(user.usrId);
    }

    /**
     * Hash code int.
     * 计算哈希值
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.usrId);
    }
}
