package cn.kevin.ims.entity;

import lombok.*;

import java.util.Date;
import java.util.Objects;

/**
 * The type Student.
 * Student
 * @author kevin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AbstractEntity {
    /**
     * 序列化参数
     */
    private static final long serialVersionUID = 1L;
    /**
     * 学生ID
     */
    private Integer stuId;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 学生性别
     */
    private Integer stuGender;
    /**
     * 学生生日
     */
    private Date stuBirthday;
    /**
     * 学生邮箱
     */
    private String stuEmail;
    /**
     * 学生年龄
     */
    private Integer stuAge;


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
