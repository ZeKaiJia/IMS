package cn.kevin.sms.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author kevin
 */
public class Subject implements Serializable {
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

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Boolean getReal() {
        return isReal;
    }

    public void setReal(Boolean real) {
        isReal = real;
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
