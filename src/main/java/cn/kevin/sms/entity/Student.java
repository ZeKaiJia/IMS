package cn.kevin.sms.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author kevin
 */
public class Student {
    private Integer stuId;
    private Integer stuAge;
    private Date stuBirthday;
    private String stuEmail;
    private Integer stuGender;
    private String stuName;

    public Student() {
        super();
    }

    public Student(Integer stuId, Integer stuAge, Date stuBirthday, String stuEmail, Integer stuGender, String stuName) {
        this.stuId = stuId;
        this.stuAge = stuAge;
        this.stuBirthday = stuBirthday;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.stuName = stuName;
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

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuAge=" + stuAge +
                ", stuBirthday=" + stuBirthday +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuGender=" + stuGender +
                ", stuName='" + stuName + '\'' +
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
        return stuId.equals(student.stuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId);
    }
}
