package cn.kevin.sms.entity;

import java.util.Objects;

/**
 * @author kevin
 */
public class Subject {
    private Integer subId;
    private String subName;
    private Integer subTeacherId;
    private Integer subCredit;

    public Subject() {
        super();
    }

    public Subject(Integer subId, String subName, Integer subTeacherId, Integer subCredit) {
        this.subId = subId;
        this.subName = subName;
        this.subTeacherId = subTeacherId;
        this.subCredit = subCredit;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getSubTeacherId() {
        return subTeacherId;
    }

    public void setSubTeacherId(Integer subTeacherId) {
        this.subTeacherId = subTeacherId;
    }

    public Integer getSubCredit() {
        return subCredit;
    }

    public void setSubCredit(Integer subCredit) {
        this.subCredit = subCredit;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subId=" + subId +
                ", subName='" + subName + '\'' +
                ", subTeacherId=" + subTeacherId +
                ", subCredit=" + subCredit +
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
                subName.equals(subject.subName) &&
                subTeacherId.equals(subject.subTeacherId) &&
                subCredit.equals(subject.subCredit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subId, subName, subTeacherId, subCredit);
    }
}
