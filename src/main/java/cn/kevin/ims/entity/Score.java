package cn.kevin.ims.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stuId;
    private Integer subId;
    private Integer subScore;
    private long gmtCreate;
    private long gmtModify;
    private Boolean isReal;

    public Score() {
        super();
        this.isReal = true;
    }

    public Score(Integer stuId, Integer subId) {
        this.stuId = stuId;
        this.subId = subId;
    }

    public Score(Integer stuId, Integer subId, Integer subScore, long gmtCreate, long gmtModify) {
        this.stuId = stuId;
        this.subId = subId;
        this.subScore = subScore;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.isReal = true;
    }

    @Override
    public String toString() {
        return "Score{" +
                "stuId=" + stuId +
                ", subId=" + subId +
                ", subScore=" + subScore +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", isReal=" + isReal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }
        Score score = (Score) o;
        return stuId.equals(score.stuId) &&
                subId.equals(score.subId) &&
                isReal.equals(score.isReal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, subId, isReal);
    }
}
