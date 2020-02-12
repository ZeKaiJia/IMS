package cn.kevin.sms.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer subId;
    private String subName;
    private Integer subTeacherId;
    private Integer subCredit;
    private long gmtCreate;
    private long gmtModify;
    private Boolean isReal;

    public Subject() {
        super();
        this.isReal = true;
    }

    public Subject(Integer subId, String subName, Integer subTeacherId, Integer subCredit, long gmtCreate, long gmtModify) {
        this.subId = subId;
        this.subName = subName;
        this.subTeacherId = subTeacherId;
        this.subCredit = subCredit;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.isReal = true;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subId=" + subId +
                ", subName='" + subName + '\'' +
                ", subTeacherId=" + subTeacherId +
                ", subCredit=" + subCredit +
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
