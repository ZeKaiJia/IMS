package cn.kevin.sms.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author kevin
 */
public class Student implements Serializable {
    private Integer stuId;
    private Integer stuAge;
    private Date stuBirthday;
    private String stuEmail;
    private Integer stuGender;
    private String stuName;
    private long gmtCreate;
    private long gmtModify;
    private Boolean isReal;

    public Student() {
        super();
        this.isReal = true;
    }

    public Student(Integer stuId, Integer stuAge, Date stuBirthday, String stuEmail, Integer stuGender, String stuName, long gmtCreate, long gmtModify) {
        this.stuId = stuId;
        this.stuAge = stuAge;
        this.stuBirthday = stuBirthday;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.stuName = stuName;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.isReal = true;
    }

    public Student(Student student) {
        this.stuId = student.stuId;
        this.stuAge = student.stuAge;
        this.stuBirthday = student.stuBirthday;
        this.stuEmail = student.stuEmail;
        this.stuGender = student.stuGender;
        this.stuName = student.stuName;
        this.gmtCreate = student.gmtCreate;
        this.gmtModify = student.gmtModify;
        this.isReal = student.isReal;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public Integer getStuGender() {
        return stuGender;
    }

    public void setStuGender(Integer stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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
        return "Student{" +
                "stuId=" + stuId +
                ", stuAge=" + stuAge +
                ", stuBirthday=" + stuBirthday +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuGender=" + stuGender +
                ", stuName='" + stuName + '\'' +
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
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return stuId.equals(student.stuId) &&
                isReal.equals(student.isReal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, isReal);
    }
}
