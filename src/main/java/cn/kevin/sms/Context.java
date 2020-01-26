package cn.kevin.sms;

import cn.kevin.sms.service.StudentService;
import cn.kevin.sms.service.SubjectService;

/**
 * @author Sean Wu
 */
public class Context {

    private static StudentService studentService = new StudentService();

    public static StudentService getStudentService() {
        return studentService;
    }
}
