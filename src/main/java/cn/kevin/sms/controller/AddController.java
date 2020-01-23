package cn.kevin.sms.controller;

import cn.kevin.sms.entity.Student;
import cn.kevin.sms.service.StudentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


/**
 * @author kevin
 */
public class AddController implements BaseController{
    private StudentService studentService = new StudentService();

    Scanner input = new Scanner(System.in);

    public AddController() throws Exception {
    }

    @Override
    public void doBiz(int choice) {
        switch (choice) {
            case 1:
                addStu();
                break;
            case 2:
                addSub();
                break;
            default:
                MenuController.unCorrect();
        }
    }

    public void addStu() {
        System.out.println("--- Please input a new student's information(use ',' separated)");
        System.out.println("--- Including id age birthday Email gender name:");
        System.out.print("--- ");
        String string = input.next();
        String[] info = string.split(",");
        Integer stuId = Integer.parseInt(info[0]);
        Integer stuAge = Integer.parseInt(info[1]);
        String stuEmail = info[3];
        Integer stuGender = Integer.parseInt(info[4]);
        String stuName = info[5];

        //以下都是日期的转换操作
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date stuBirthday = null;
        try {
            stuBirthday = simpleDateFormat.parse(info[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        assert stuBirthday != null;
        calendar.setTime(stuBirthday);
        calendar.add(Calendar.DATE,1);
        stuBirthday = calendar.getTime();

        Student student = new Student(stuId, stuAge, stuBirthday, stuEmail, stuGender, stuName);
        if (studentService.insert(student) != null) {
            System.out.println("--- Insert student information successfully!");
        }
        else {
            System.out.println("--- Insert student information failed!");
        }
    }
    public void addSub() {

    }
}
