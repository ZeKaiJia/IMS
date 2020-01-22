package cn.kevin.sms.entity;

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
}
