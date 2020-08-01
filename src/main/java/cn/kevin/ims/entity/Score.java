package cn.kevin.ims.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
@ToString
public class Score extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private Integer stuId;
    private Integer subId;
    private Integer subScore;

    public Score() {
        super();
    }

    public Score(Integer stuId, Integer subId) {
        this.stuId = stuId;
        this.subId = subId;
    }

    public Score(Integer stuId, Integer subId, Integer subScore, long utcCreate, long utcModify) {
        super(utcCreate, utcModify);
        this.stuId = stuId;
        this.subId = subId;
        this.subScore = subScore;
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
        return this.stuId.equals(score.stuId) &&
                this.subId.equals(score.subId) &&
                this.getIsReal().equals(score.getIsReal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stuId, this.subId, this.getIsReal());
    }
}
