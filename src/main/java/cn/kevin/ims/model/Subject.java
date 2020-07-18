package cn.kevin.ims.model;

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
public class Subject extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private Integer subId;
    private String subName;
    private String subTeacherId;
    private Integer subCredit;

    public Subject() {
        super();
    }

    public Subject(Integer subId, String subName, String subTeacherId, Integer subCredit, long utcCreate, long utcModify) {
        super(utcCreate, utcModify);
        this.subId = subId;
        this.subName = subName;
        this.subTeacherId = subTeacherId;
        this.subCredit = subCredit;
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
        return this.subId.equals(subject.subId) &&
                this.getIsReal().equals(subject.getIsReal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.subId, this.getIsReal());
    }
}
