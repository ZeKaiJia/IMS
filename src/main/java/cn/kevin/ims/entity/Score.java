package cn.kevin.ims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Objects;


/**
 * 成绩POJO类
 * Score
 * @Author: Kevin
 * @Date: 2020 /8/1 3:07 下午
 */
@ApiModel(description = "成绩实体", value = "成绩实体类")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Score extends BaseEntity {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 488188911958680866L;
    /**
     * 学号
     * The Stu id.
     */
    @ApiModelProperty(value = "学号", required = true)
    private String stuId;
    /**
     * 课程号
     * The Sub id.
     */
    @ApiModelProperty(value = "课程号", required = true)
    private Integer subId;
    /**
     * 课程分数
     * The Sub score.
     */
    @ApiModelProperty(value = "课程分数", required = true)
    private Integer subScore;

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
