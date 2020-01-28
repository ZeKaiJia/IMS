package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.entity.Student;
import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.service.ScoreService;
import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.service.SubjectService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Scanner;

/**
 * @author kevin
 */
public class UpdateController extends BaseController {
    @Resource(name = "studentService")
    private StudentService studentService;
    @Resource(name = "subjectService")
    private SubjectService subjectService;
    @Resource(name = "scoreService")
    private ScoreService scoreService;

    private Scanner input = new Scanner(System.in);

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
        Student stuTemp = studentService.update(student);
        if ( stuTemp != null) {
            System.out.println("--- Update student information successfully!");
            System.out.println("--- INFO: ");
            System.out.println(stuTemp);
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
        Subject subTemp = subjectService.update(subject);
        if ( subTemp != null) {
            System.out.println("--- Update subject information successfully!");
            System.out.println("--- INFO: ");
            System.out.println(subTemp);
        }
        else {
            System.out.println("--- Update subject information failed!");
        }
    }

    private void updateScore() {
        System.out.println("--- Please input a fixed score's information(use ',' separated)");
        System.out.println("--- Including student ID subject ID score:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer stuId = Integer.parseInt(info[0]);
        Integer subId = Integer.parseInt(info[1]);
        Integer subScore = Integer.parseInt(info[2]);
        Score score = new Score(stuId, subId, "", subScore, 0, System.currentTimeMillis());
        Score scoreTemp = scoreService.update(score);
        if ( scoreTemp != null) {
            System.out.println("--- Update score information successfully!");
            System.out.println("--- INFO: ");
            System.out.println(scoreTemp);
        }
        else {
            System.out.println("--- Update score information failed!");
        }
    }
}
