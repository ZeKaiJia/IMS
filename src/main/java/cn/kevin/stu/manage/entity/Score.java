package cn.kevin.stu.manage.entity;

/**
 * @author kevin
 */
public class Score {
    private Integer stuId;      //Student
    private Integer subId;      //Subject
    private String subName;     //Subject
    private Integer subScore;   //this
    private long gmtCreate;     //this
    private long gmtModify;     //this

    public Score() {
        super();
    }

    public Score(Integer stuId, Integer subId, String subName, Integer subScore, long gmtCreate, long gmtModify) {
        this.stuId = stuId;
        this.subId = subId;
        this.subName = subName;
        this.subScore = subScore;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
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

    public Integer getSubScore() {
        return subScore;
    }

    public void setSubScore(Integer subScore) {
        this.subScore = subScore;
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

    @Override
    public String toString() {
        return "Score{" +
                "stuId=" + stuId +
                ", subId=" + subId +
                ", subName='" + subName + '\'' +
                ", subScore=" + subScore +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
