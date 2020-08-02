package cn.kevin.ims.entity;

import lombok.*;

import java.util.Objects;

/**
 * The type Score.
 * Score
 * @author kevin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Score extends AbstractEntity {
    /**
     * 序列化参数
     */
    private static final long serialVersionUID = 488188911958680866L;
    /**
     * 学生ID
     */
    private Integer stuId;
    /**
     * 课程ID
     */
    private Integer subId;
    /**
     * 课程分数
     */
    private Integer subScore;

    /**
     * Instantiates a new Score.
     * 双参数构造函数
     * @param stuId the stu id
     * @param subId the sub id
     */
    public Score(Integer stuId, Integer subId) {
        this.stuId = stuId;
        this.subId = subId;
    }

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
        if (!(o instanceof Score)) {
            return false;
        }
        Score score = (Score) o;
        return this.stuId.equals(score.stuId) &&
                this.subId.equals(score.subId);
    }

    /**
     * Hash code int.
     * 计算哈希值
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.stuId, this.subId);
    }
}
