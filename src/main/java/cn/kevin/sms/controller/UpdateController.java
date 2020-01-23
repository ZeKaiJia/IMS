package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.service.SubjectService;

import java.util.Date;
import java.util.Scanner;

/**
 * @author kevin
 */
public class UpdateController implements BaseController {
    private StudentService studentService = new StudentService();
    private SubjectService subjectService = new SubjectService();

    private Scanner input = new Scanner(System.in);

    UpdateController() throws Exception {
    }

    @Override
    public void doBiz(int choice) {
        switch (choice) {
            case 1:
                updateStu();
                break;
            case 2:
                updateSub();
                break;
            case 3:
                updateScore();
                break;
            default:
                MenuController.unCorrect();
        }
    }

    private void updateStu() {
        System.out.println("--- Please input a fixed student's information(use ',' separated)");
        System.out.println("--- Including id age birthday Email gender name:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer stuId = Integer.parseInt(info[0]);
        Integer stuAge = Integer.parseInt(info[1]);
        Date stuBirthday = MenuController.dateTranslate(info[2]);
        String stuEmail = info[3];
        Integer stuGender = Integer.parseInt(info[4]);
        String stuName = info[5];

        Student student = new Student(stuId, stuAge, stuBirthday, stuEmail, stuGender, stuName);
        if (studentService.update(student) != null) {
            System.out.println("--- Update student information successfully!");
        }
        else {
            System.out.println("--- Update student information failed!");
        }
    }

    private void updateSub() {
        System.out.println("--- Please input a fixed subject's information(use ',' separated)");
        System.out.println("--- Including ID name teacherID credit:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer subId = Integer.parseInt(info[0]);
        String subName = info[1];
        Integer subTeacherId = Integer.parseInt(info[2]);
        Integer subCredit = Integer.parseInt(info[3]);

        Subject subject = new Subject(subId, subName, subTeacherId, subCredit);
        if (subjectService.update(subject) != null) {
            System.out.println("--- Update subject information successfully!");
        }
        else {
            System.out.println("--- Update subject information failed!");
        }
    }

    private void updateScore() {

    }
}
