package cn.kevin.ims.entity;

import lombok.*;

import java.util.Date;
import java.util.Objects;

/**
 * The type Student.
 * Student
 * 学生POJO类
 * @author kevin
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -3103418538707967614L;
    /**
     * 学号
     * The Stu id.
     */
    private String stuId;
    /**
     * 学生姓名
     * The Stu name.
     */
    private String stuName;
    /**
     * 学生性别
     * The Stu gender.
     */
    private Integer stuGender;
    /**
     * 学生生日
     * The Stu birthday.
     */
    private Date stuBirthday;
    /**
     * 学生邮箱
     * The Stu email.
     */
    private String stuEmail;
    /**
     * 学生联系电话
     * The Stu phone.
     */
    private String stuPhone;

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
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return this.stuId.equals(student.stuId);
    }

    /**
     * Hash code int.
     * 计算哈希值
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.stuId);
    }
}
