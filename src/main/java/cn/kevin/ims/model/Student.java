package cn.kevin.ims.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

/**
 * @author kevin
 */
@Getter
@Setter
@ToString
public class Student extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private Integer stuId;
    private Integer stuAge;
    private Date stuBirthday;
    private String stuEmail;
    private Integer stuGender;
    private String stuName;

    public Student() {
        super();
    }

    public Student(Student student) {
        super(student.getUtcCreate(), student.getUtcModify());
        this.stuId = student.stuId;
        this.stuAge = student.stuAge;
        this.stuBirthday = student.stuBirthday;
        this.stuEmail = student.stuEmail;
        this.stuGender = student.stuGender;
        this.stuName = student.stuName;
    }

    public Student(Integer stuId, Integer stuAge, Date stuBirthday, String stuEmail, Integer stuGender, String stuName, long utcCreate, long utcModify) {
        super(utcCreate, utcModify);
        this.stuId = stuId;
        this.stuAge = stuAge;
        this.stuBirthday = stuBirthday;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.stuName = stuName;
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
        return this.stuId.equals(student.stuId) &&
                this.getIsReal().equals(student.getIsReal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stuId, this.getIsReal());
    }
}
