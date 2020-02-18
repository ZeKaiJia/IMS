package cn.kevin.ims.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stuId;
    private Integer stuAge;
    private Date stuBirthday;
    private String stuEmail;
    private Integer stuGender;
    private String stuName;
    private long utcCreate;
    private long utcModify;
    private Boolean isReal;

    public Student() {
        super();
        this.isReal = true;
    }

    public Student(Student student) {
        this.stuId = student.stuId;
        this.stuAge = student.stuAge;
        this.stuBirthday = student.stuBirthday;
        this.stuEmail = student.stuEmail;
        this.stuGender = student.stuGender;
        this.stuName = student.stuName;
        this.utcCreate = student.utcCreate;
        this.utcModify = student.utcModify;
        this.isReal = student.isReal;
    }

    public Student(Integer stuId, Integer stuAge, Date stuBirthday, String stuEmail, Integer stuGender, String stuName, long utcCreate, long utcModify) {
        this.stuId = stuId;
        this.stuAge = stuAge;
        this.stuBirthday = stuBirthday;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.stuName = stuName;
        this.utcCreate = utcCreate;
        this.utcModify = utcModify;
        this.isReal = true;
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
