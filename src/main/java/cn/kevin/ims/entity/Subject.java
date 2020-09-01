package cn.kevin.ims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Objects;

/**
 * The type Subject.
 * Subject
 * 课程POJO类
 * @author kevin
 */
@ApiModel(description = "课程实体", value = "课程实体类")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends BaseEntity {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 9037415837973538853L;
    /**
     * 课程号
     * The Sub id.
     */
    @ApiModelProperty(value = "课程号", required = true)
    private Integer subId;
    /**
     * 课程名
     * The Sub name.
     */
    @ApiModelProperty(value = "课程名", required = true)
    private String subName;
    /**
     * 课程授课教师号
     * The Sub teacher id.
     */
    @ApiModelProperty(value = "课程授课教师号", required = true)
    private String subTeacherId;
    /**
     * 课程学分
     * The Sub credit.
     */
    @ApiModelProperty(value = "课程学分", required = true)
    private Double subCredit;


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
        if (!(o instanceof Subject)) {
            return false;
        }
        Subject subject = (Subject) o;
        return this.subId.equals(subject.subId);
    }


    /**
     * Hash code int.
     * 计算哈希值
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.subId);
    }
}
