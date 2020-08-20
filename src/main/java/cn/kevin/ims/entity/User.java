package cn.kevin.ims.entity;

import lombok.*;

import java.util.Objects;

/**
 * 用户实体类
 * User
 * 用户POJO类
 * @Author: Kevin
 * @Date: 2020 /2/18 10:56 下午
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -5026828438186547513L;
    /**
     * 用户名
     * The Usr name.
     */
    private String usrName;
    /**
     * 密码
     * The Usr password.
     */
    private String usrPassword;
    /**
     * 昵称
     * The Usr nick.
     */
    private String usrNick;
    /**
     * 联系电话
     * The Usr phone.
     */
    private String usrPhone;
    /**
     * 电子邮箱
     * The Usr email.
     */
    private String usrEmail;
    /**
     * 最近登录
     * The Last login.
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
        User sysUser = (User) o;
        return this.usrName.equals(sysUser.usrName);
    }

    /**
     * Hash code int.
     * 计算哈希值
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.usrName);
    }
}
