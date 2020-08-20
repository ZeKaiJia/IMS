package cn.kevin.ims.entity;

import lombok.*;

import java.util.Objects;

/**
 * The type Subject.
 * Subject
 * 课程POJO类
 * @author kevin
 */
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
    private Integer subId;
    /**
     * 课程名
     * The Sub name.
     */
    private String subName;
    /**
     * 课程授课教师号
     * The Sub teacher id.
     */
    private String subTeacherId;
    /**
     * 课程学分
     * The Sub credit.
     */
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
