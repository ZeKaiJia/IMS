package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Score;
import cn.kevin.sms.service.ScoreService;
import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.service.SubjectService;

import java.util.List;
import java.util.Scanner;

/**
 * @author kevin
 */
public class DeleteController implements BaseController {
    private StudentService studentService = new StudentService();
    private SubjectService subjectService = new SubjectService();
    private ScoreService scoreService = new ScoreService();

    private Scanner input = new Scanner(System.in);

    DeleteController() throws Exception {
    }

    @Override
    public void doBiz(int choice) {
        switch (choice) {
            case 1:
                deleteStu();
                break;
            case 2:
                deleteSub();
                break;
            case 3:
                deleteScore();
                break;
            default:
                MenuController.unCorrect();
        }
    }

    private void deleteStu() {
        System.out.print("--- Please input the student's ID which you want to delete: ");
        Integer stuId = input.nextInt();
        if (studentService.delete(stuId) != null) {
            System.out.println("--- Delete student information successfully!");
        }
        else {
            System.out.println("--- Delete student information failed!");
        }
    }

    private void deleteSub() {
        System.out.print("--- Please input the subject's ID which you want to delete: ");
        Integer subId = input.nextInt();
        if (subjectService.delete(subId) != null) {
            System.out.println("--- Delete subject information successfully!");
        }
        else {
            System.out.println("--- Delete subject information failed!");
        }
    }

    private void deleteScore() {
        MenuController.deleteSco();
        int stuId;
        int subId;
        switch (input.nextInt()) {
            case 1:
                System.out.print("--- Student ID: ");
                stuId = input.nextInt();
                System.out.print("--- Subject ID: ");
                subId = input.nextInt();
                Score score = scoreService.delete(stuId, subId);
                if ( score != null ) {
                    System.out.println("--- Delete score information successfully!");
                }
                else {
                    System.out.println("--- Delete score information failed!");
                }
                break;
            case 2:
                System.out.print("--- Student ID: ");
                stuId = input.nextInt();
                List<Score> scores = scoreService.deleteAll(stuId);
                if ( scores.size() != 0 ) {
                    System.out.println("--- Delete score information successfully!");
                }
                else {
                    System.out.println("--- Delete score information failed!");
                }
                break;
            default:
                MenuController.unCorrect();
        }
    }
}
