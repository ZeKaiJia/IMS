package cn.kevin.sms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stuId;
    private Integer stuAge;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "GMT+8")
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
