package cn.kevin.ims.entity;

import lombok.*;

import java.util.Objects;

/**
 * The type Subject.
 * Subject
 * @author kevin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends AbstractEntity {
    /**
     * 序列化参数
     */
    private static final long serialVersionUID = 9037415837973538853L;
    /**
     * 课程ID
     */
    private Integer subId;
    /**
     * 课程名称
     */
    private String subName;
    /**
     * 授课教师
     */
    private String subTeacherId;
    /**
     * 课程学分
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
