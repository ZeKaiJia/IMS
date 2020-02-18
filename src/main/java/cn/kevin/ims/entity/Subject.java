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
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer subId;
    private String subName;
    private Integer subTeacherId;
    private Integer subCredit;
    private long utcCreate;
    private long utcModify;
    private Boolean isReal;

    public Subject() {
        super();
        this.isReal = true;
    }

    public Subject(Integer subId, String subName, Integer subTeacherId, Integer subCredit, long utcCreate, long utcModify) {
        this.subId = subId;
        this.subName = subName;
        this.subTeacherId = subTeacherId;
        this.subCredit = subCredit;
        this.utcCreate = utcCreate;
        this.utcModify = utcModify;
        this.isReal = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }
        Subject subject = (Subject) o;
        return subId.equals(subject.subId) &&
                isReal.equals(subject.isReal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subId, isReal);
    }
}
