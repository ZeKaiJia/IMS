package com.jzk.sms.service;

import com.jzk.sms.entity.Student;
import com.jzk.sms.Subject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Sean Wu
 */
public class StudentService {

    private Set<Student> studentCage = new TreeSet<>();

    private Map<Integer, Student> idStuMap = new HashMap<>();

    private Map<String, Student> nameStuMap = new HashMap<>();

    private Set<Subject> subjectCage = new TreeSet<>();


    //供其他功能使用的查找方法，可以通过ID、名称来查找
    public  Student findByID(int id) {
        return idStuMap.get(id);
    }
    public  Student findByName(String name) {
        return nameStuMap.get(name);
    }

    public  Subject findByIDSubject(int ID) {
        return subjectCage.stream().filter(e -> ID == e.ID).findAny().orElse(null);
    }

    public  Subject findByNameSubject(String name) {
        return subjectCage.stream().filter(e -> name.equals(e.name)).findAny().orElse(null);
    }
}
