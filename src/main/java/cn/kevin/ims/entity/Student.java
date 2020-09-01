package cn.kevin.ims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.Objects;

/**
 * The type Student.
 * Student
 * 学生POJO类
 * @author kevin
 */
@ApiModel(description = "学生实体", value = "学生实体类")
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
    @ApiModelProperty(value = "学号", required = true)
    private String stuId;
    /**
     * 姓名
     * The Stu name.
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String stuName;
    /**
     * 性别
     * The Stu gender.
     */
    @ApiModelProperty(value = "性别", required = true)
    private Integer stuGender;
    /**
     * 生日
     * The Stu birthday.
     */
    @ApiModelProperty(value = "生日", required = true)
    private Date stuBirthday;
    /**
     * 邮箱
     * The Stu email.
     */
    @ApiModelProperty(value = "邮箱", required = true)
    private String stuEmail;
    /**
     * 联系电话
     * The Stu phone.
     */
    @ApiModelProperty(value = "联系电话", required = true)
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
