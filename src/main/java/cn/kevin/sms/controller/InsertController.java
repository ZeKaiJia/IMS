package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.entity.Student;
import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.service.ScoreService;
import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.service.SubjectService;

import java.util.Date;
import java.util.Scanner;


/**
 * @author kevin
 */
public class InsertController implements BaseController{
    private StudentService studentService = new StudentService();
    private SubjectService subjectService = new SubjectService();
    private ScoreService scoreService = new ScoreService();

    private Scanner input = new Scanner(System.in);

    private Student student;
    private Subject subject;

    InsertController() throws Exception {
    }

    @Override
    public void doBiz(int choice) {
        switch (choice) {
            case 1:
                insertStu();
                break;
            case 2:
                insertSub();
                break;
            case 3:
                insertScore();
                break;
            default:
                MenuController.unCorrect();
        }
    }

    public void insertStu() {
        System.out.println("--- Please input a new student's information(use ',' separated)");
        System.out.println("--- Including ID age birthday Email gender name:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer stuId = Integer.parseInt(info[0]);
        Integer stuAge = Integer.parseInt(info[1]);
        Date stuBirthday = MenuController.dateTranslate(info[2]);
        String stuEmail = info[3];
        Integer stuGender = Integer.parseInt(info[4]);
        String stuName = info[5];

        student = new Student(stuId, stuAge, stuBirthday, stuEmail, stuGender, stuName);
        if (studentService.insert(student) != null) {
            System.out.println("--- Insert student information successfully!");
        }
        else {
            System.out.println("--- Insert student information failed!");
        }
    }
    public void insertSub() {
        System.out.println("--- Please input a new subject's information(use ',' separated)");
        System.out.println("--- Including ID name teacherID credit:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer subId = Integer.parseInt(info[0]);
        String subName = info[1];
        Integer subTeacherId = Integer.parseInt(info[2]);
        Integer subCredit = Integer.parseInt(info[3]);

        subject = new Subject(subId, subName, subTeacherId, subCredit);
        if (subjectService.insert(subject) != null) {
            System.out.println("--- Insert subject information successfully!");
        }
        else {
            System.out.println("--- Insert subject information failed!");
        }
    }

    public void insertScore() {
        System.out.println("--- Please input a new score's information(use ',' separated)");
        System.out.println("--- Including studentID subjectID score:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer stuId = Integer.parseInt(info[0]);
        Integer subId = Integer.parseInt(info[1]);
        Integer subScore = Integer.parseInt(info[2]);
        student = studentService.select(stuId);
        subject = subjectService.select(subId);
        if ( student == null || subject == null ) {
            System.out.println("--- No such student or subject!");
        }
        else {
            Score score = new Score(stuId, subId, subject.getSubName(), subScore, System.currentTimeMillis(), System.currentTimeMillis());
            if ( scoreService.insert(score) != null ) {
                System.out.println("--- Insert score information successfully!");
            }
            else {
                System.out.println("--- Insert score information failed!");
            }
        }
    }
}
