package cn.kevin.ims.entity;

import lombok.*;

import java.util.Objects;

/**
 * 用户实体类
 * User
 *
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
    private static final long serialVersionUID = -5026828438186547513L;
    /**
     * 用户名
     */
    private String usrName;
    /**
     * 用户密码
     */
    private String usrPassword;
    /**
     * 用户昵称
     */
    private String usrNick;
    /**
     * 用户联系电话
     */
    private String usrPhone;
    /**
     * 用户电子邮箱
     */
    private String usrEmail;
    /**
     * 最近登录时间
     */
    private long lastLogin;

    /**
     * Equals boolean.
     * 判断类型
     *
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
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.usrName);
    }
}
