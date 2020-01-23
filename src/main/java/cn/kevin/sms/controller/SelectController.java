package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.entity.Subject;
import cn.kevin.sms.service.SubjectService;

import java.util.List;
import java.util.Scanner;

public class SelectController implements BaseController {
    private StudentService studentService = new StudentService();
    private SubjectService subjectService = new SubjectService();

    private Scanner input = new Scanner(System.in);

    private List<Student> students;
    private List<Subject> subjects;


    SelectController() throws Exception {
    }

    @Override
    public void doBiz(int choice) {
        switch (choice) {
            case 1:
                selectStu();
                break;
            case 2:
                selectSub();
                break;
            case 3:
                selectScore();
                break;
            default:
                MenuController.unCorrect();
        }
    }

    private void selectStu() {
        MenuController.selectStu();
        Student student = new Student();
        boolean flag = true;
        switch (input.nextInt()) {
            case 1:
                System.out.print("--- ID: ");
                student.setStuId(input.nextInt());
                break;
            case 2:
                System.out.print("--- Age: ");
                student.setStuAge(input.nextInt());
                break;
            case 3:
                System.out.print("--- Name: ");
                student.setStuName(input.next());
                break;
//            case 4:
//                System.out.print("--- Birthday: ");
//                String strDate = input.next();
//                student.setStuBirthday(MenuController.dateTranslate(strDate));
//                break;
            case 4:
                System.out.print("--- Email: ");
                student.setStuEmail(input.next());
                break;
            case 5:
                System.out.print("--- Gender: ");
                student.setStuGender(input.nextInt());
                break;
            case 6:
                System.out.print("--- Last name: ");
                students = studentService.selectSimilarName(input.next());
                if (students.size() == 0) {
                    System.out.println("--- No information in Student Database!");
                } else {
                    students.forEach(System.out::println);
                }
                flag = false;
                break;
            case 7:
                students = studentService.selectAll();
                if (students.size() == 0) {
                    System.out.println("--- No information in Student Database!");
                } else {
                    students.forEach(System.out::println);
                }
                flag = false;
                break;
            default:
                MenuController.unCorrect();
        }
        if (flag) {
            students = studentService.selectByAllInfo(student);
            if (students.size() == 0) {
                System.out.println("--- No information in Student Database!");
            } else {
                students.forEach(System.out::println);
            }
        }
    }

    private void selectSub() {
        MenuController.selectSub();
        Subject subject = new Subject();
        boolean flag = true;
        switch (input.nextInt()) {
            case 1:
                System.out.print("--- ID: ");
                subject.setSubId(input.nextInt());
                break;
            case 2:
                System.out.print("--- Full name: ");
                subject.setSubName(input.next());
                break;
            case 3:
                System.out.print("--- Teacher ID: ");
                subject.setSubTeacherId(input.nextInt());
                break;
            case 4:
                System.out.print("--- Credit: ");
                subject.setSubCredit(input.nextInt());
                break;
            case 5:
                System.out.print("--- Part name: ");
                subjects = subjectService.selectSimilarName(input.next());
                if (subjects.size() == 0) {
                    System.out.println("--- No information in Subject Database!");
                } else {
                    subjects.forEach(System.out::println);
                }
                flag = false;
                break;
            case 6:
                subjects = subjectService.selectAll();
                if (subjects.size() == 0) {
                    System.out.println("--- No information in Subject Database!");
                } else {
                    subjects.forEach(System.out::println);
                }
                flag = false;
                break;
            default:
                MenuController.unCorrect();
        }
        if (flag) {
            subjects = subjectService.selectByAllInfo(subject);
            if (subjects.size() == 0) {
                System.out.println("--- No information in Subject Database!");
            } else {
                subjects.forEach(System.out::println);
            }
        }
    }

    private void selectScore() {

    }
}
