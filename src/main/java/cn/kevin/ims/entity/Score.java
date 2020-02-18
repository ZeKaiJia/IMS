package cn.kevin.ims.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
@ToString
public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stuId;
    private Integer subId;
    private Integer subScore;
    private long utcCreate;
    private long utcModify;
    private Boolean isReal;

    public Score() {
        super();
        this.isReal = true;
    }

    public Score(Integer stuId, Integer subId) {
        this.stuId = stuId;
        this.subId = subId;
    }

    public Score(Integer stuId, Integer subId, Integer subScore, long utcCreate, long utcModify) {
        this.stuId = stuId;
        this.subId = subId;
        this.subScore = subScore;
        this.utcCreate = utcCreate;
        this.utcModify = utcModify;
        this.isReal = true;
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
