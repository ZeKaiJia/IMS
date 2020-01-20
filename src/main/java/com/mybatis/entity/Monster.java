package com.mybatis.entity;

import java.util.Date;

public class Monster {
    private Integer monster_id;
    private Integer age;
    private Date birthday;
    private String email;
    private Integer gender;
    private String name;
    private double salary;

    public Monster() {
        super();
    }

    public Monster(Integer monster_id, Integer age, Date birthday, String email, Integer gender, String name, double salary) {
        this.monster_id = monster_id;
        this.age = age;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.salary = salary;
    }

    public Integer getMonster_id() {
        return monster_id;
    }

    public void setMonster_id(Integer monster_id) {
        this.monster_id = monster_id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monster_id=" + monster_id +
                ", age=" + age +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
