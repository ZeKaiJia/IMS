package com.jzk.sms.controller;

import com.jzk.sms.DbUtil;
import com.jzk.sms.entity.Student;
import com.jzk.sms.service.StudentService;

import java.util.Scanner;

/**
 * @author Sean Wu
 */

public class DelController implements BaseController {

    private StudentService studentService = new StudentService();

    @Override
    public void doBiz() {
        add();
    }

    //增加学生信息
    public boolean add() {
        int ID;
        String name;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入学生ID: ");
        ID = input.nextInt();
        System.out.print("---请输入学生姓名: ");
        name = input.next();

        Student stu = new Student(ID, name);
        Student temp = studentService.findByID(ID);
        if (temp != null) {
            return false;
            // repeatFind(1);
        }
           /* for (Subject sub : subjectCage) {
                update_new(stu, sub.name);
            }
            addAPI(stu);*/
        DbUtil.addStudent(stu);
        return true;

    }
}
