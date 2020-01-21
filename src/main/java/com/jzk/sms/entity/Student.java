package com.jzk.sms.entity;

import com.jzk.sms.DbUtil;
import com.jzk.sms.Manager;
import com.jzk.sms.Subject;

import java.util.*;

public class Student implements Comparable{
    private int ID;
    private String name;
    private Map<Subject, Integer> subject;

    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
        subject = new TreeMap<>();
        for (int i = 0; i<Manager.getSubjectCage().size(); i++) {
            Subject sub = new Subject(DbUtil.getSubjectData().get(i).ID,DbUtil.getSubjectData().get(i).name,DbUtil.getSubjectData().get(i).GP);
            subject.put(sub, null);
        }
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Integer getSubjectScore(String name) {
        for (Map.Entry<Subject, Integer> entry : subject.entrySet()) {
            if (entry.getKey().name.equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void setSubject(String sub, Integer score) {
        Set<Subject> keyset = subject.keySet();
        for (Subject temp : keyset) {
            if ( temp.name.equals(sub) ) {
                subject.put(temp, score);
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        Student p = (Student) o;
        return this.ID - p.ID;
    }
}
