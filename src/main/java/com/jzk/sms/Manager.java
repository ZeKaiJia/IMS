package com.jzk.sms;

import com.jzk.sms.controller.AddController;
import com.jzk.sms.controller.BaseController;
import com.jzk.sms.controller.DelController;
import com.jzk.sms.entity.Student;

import java.util.*;

public class Manager {
    public static boolean flag = false;


    public static void main(String[] args) {
        Map<Integer, BaseController> controllerMap = new HashMap<>();

        controllerMap.put(1, new AddController());
        controllerMap.put(2, new DelController());




        Scanner input = new Scanner(System.in);
        Menu.show();
        DbUtil.initialMySQL();
        while (!flag) {
            Menu.showChoose();
            int choose = Menu.choose();
            BaseController controller = controllerMap.get(choose);
            if (controller == null) {
                System.out.println("没有这个controller！");
                continue;
            }
            controller.doBiz();


            /*switch (choose) {
                //增加学生信息
                case 1:
                    add();
                    break;
                //删除学生信息
                case 2:
                    delete();
                    break;
                //修改学生成绩
                case 3:
                    edit();
                    break;
                //查找学生信息
                case 4:
                    search();
                    break;
                //全部学生信息
                case 5:
                    display();
                    break;
                //增加教学科目
                case 6:
                    addSubject();
                    break;
                //删除教学科目
                case 7:
                    deleteSubject();
                    break;
                //全部科目信息
                case 8:
                    displaySubject();
                    break;
                //查找科目信息
                case 9:
                    searchSubject();
                    break;
                //退出管理系统
                case 10:
                    flag = true;
                    break;
                case 11:
                    int ID;
                    System.out.print("---请输入查找学生的ID: ");
                    ID = input.nextInt();
                    DbUtil.findStudentList(ID);
                    break;
                case 12:
                    System.out.print("---请输入查找课程的ID: ");
                    ID = input.nextInt();
                    DbUtil.findSubjectList(ID);
                    break;
            }*/
        }
        ;
    }

    public static Set<Subject> getSubjectCage() {
        return subjectCage;
    }



    //增加教学科目
    public static void addSubject() {
        int ID;
        String name;
        int GP;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入课程ID: ");
        ID = input.nextInt();
        System.out.print("---请输入课程名称: ");
        name = input.next();
        System.out.print("---请输入课程GP: ");
        GP = input.nextInt();
        Subject sub = new Subject(ID, name, GP);
        Subject tempI = findByIDSubject(ID);
        Subject tempN = findByNameSubject(name);
        if (tempI != null || tempN != null) {
            repeatFind(0);
        } else {
            addAPISub(sub);
            DbUtil.addSubject(sub);
        }
    }

    //供工具类和本类同时使用的添加方法，用于数据同步
    public static void addAPI(Student stu) {
        studentCage.add(stu);
    }

    public static void addAPISub(Subject sub) {
        subjectCage.add(sub);
    }

    //没有查找到信息后的反馈功能
    public static void NotFind(int section) {
        if (section == 1) {
            System.out.println("---查无此人!");
        } else {
            System.out.println("---查无此课!");
        }
    }

    //重复查找到信息后的反馈功能
    public static void repeatFind(int section) {
        if (section == 1) {
            System.out.println("---学生ID重复!");
        } else {
            System.out.println("---课程ID或名称重复!");
        }
    }



    //删除学生信息
    public static void delete() {
        int choice, ID;
        String name;
        Scanner input = new Scanner(System.in);
        Student stu = null;
        System.out.print("---查找方式:1.ID  2.姓名\n---选择序号:");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("---请输入删除学生的ID: ");
                ID = input.nextInt();
                stu = findByID(ID);
                break;
            case 2:
                System.out.print("---请输入删除学生的姓名: ");
                name = input.next();
                stu = findByName(name);
                break;
        }
        if (stu != null) {
            studentCage.remove(stu);
            DbUtil.delete(stu);
            System.out.println("---删除成功");
        } else {
            NotFind(1);
        }
    }

    //删除教学科目
    public static void deleteSubject() {
        int choice, ID;
        String name;
        Scanner input = new Scanner(System.in);
        Subject sub = null;
        System.out.print("---查找方式:1.ID  2.名称\n---选择序号:");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("---请输入删除课程的ID: ");
                ID = input.nextInt();
                sub = findByIDSubject(ID);
                break;
            case 2:
                System.out.print("---请输入删除课程的名称: ");
                name = input.next();
                sub = findByNameSubject(name);
                break;
        }
        if (sub != null) {
            subjectCage.remove(sub);
            DbUtil.deleteSubject(sub);
            System.out.println("---删除成功");
        } else {
            NotFind(0);
        }
    }

    //修改学生成绩
    public static void edit() {
        int choice, ID;
        String name;
        Scanner input = new Scanner(System.in);
        Student stu = null;
        System.out.print("---查找方式:1.ID  2.姓名\n---选择序号:");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("---请输入修改学生的ID: ");
                ID = input.nextInt();
                stu = findByID(ID);
                break;
            case 2:
                System.out.print("---请输入修改学生的姓名: ");
                name = input.next();
                stu = findByName(name);
                break;
        }
        if (stu != null) {
            System.out.print("---请输入要修改的科目名称: ");
            name = input.next();
            update(stu, name);
            System.out.println("---修改成功");
        } else {
            NotFind(1);
        }
    }

    //初始化成绩
    public static void update_new(Student stu, String name) {
        int score;
        Scanner input = new Scanner(System.in);
        System.out.print("---" + name + "成绩: ");
        score = input.nextInt();
        stu.setSubject(name, score);
    }

    //修改单科成绩
    public static void update(Student stu, String name) {
        int score;
        Scanner input = new Scanner(System.in);
        System.out.print("---" + name + "成绩: ");
        score = input.nextInt();
        stu.setSubject(name, score);
        DbUtil.update(stu, name, score);
    }

    //查找学生信息
    public static void search() {
        int ID, choice;
        String name;
        Student stu = null;
        Scanner input = new Scanner(System.in);
        System.out.print("---查找方式:1.ID  2.姓名\n---选择序号:");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("---请输入查找学生的ID: ");
                ID = input.nextInt();
                stu = findByID(ID);
                break;
            case 2:
                System.out.print("---请输入查找学生的姓名: ");
                name = input.next();
                stu = findByName(name);
                break;
        }
        if (stu != null) {
            System.out.println("---ID: " + stu.getID());
            System.out.println("---姓名: " + stu.getName());
            for (int i = 0; i < subjectCage.size(); i++) {
                System.out.println("---" + DbUtil.getSubjectData().get(i).name + ": " + stu.getSubjectScore(DbUtil.getSubjectData().get(i).name));
            }
        } else {
            NotFind(1);
        }
    }

    //查找科目信息
    public static void searchSubject() {
        int ID, choice;
        String name;
        Subject sub = null;
        Scanner input = new Scanner(System.in);
        System.out.print("---查找方式:1.ID  2.名称\n---选择序号:");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("---请输入查找课程的ID: ");
                ID = input.nextInt();
                sub = findByIDSubject(ID);
                break;
            case 2:
                System.out.print("---请输入查找课程的名称: ");
                name = input.next();
                sub = findByNameSubject(name);
                break;
        }
        if (sub != null) {
            System.out.println("---ID: " + sub.ID);
            System.out.println("---名称: " + sub.name);
            System.out.println("---GP: " + sub.GP);
        } else {
            NotFind(0);
        }
    }

    //全部学生信息--懒得用printf的显示全部学生信息
    public static void display() {
        StringBuilder title = new StringBuilder("---学生信息表---");
        StringBuilder sub = new StringBuilder("  ID   姓名");
        for (int i = 0; i < subjectCage.size(); i++) {
            title.insert(0, "--");
            title.append("--");
            sub.append("  ").append(DbUtil.getSubjectData().get(i).name);
        }
        System.out.println(title);
        System.out.println(sub);
        for (Student stu : studentCage) {
            System.out.print("  " + stu.getID() + "号  " + stu.getName());
            for (int i = 0; i < subjectCage.size(); i++) {
                System.out.print("   " + stu.getSubjectScore(DbUtil.getSubjectData().get(i).name));
            }
            System.out.println();
        }
    }

    //全部科目信息
    private static void displaySubject() {
        System.out.println("-------科目信息表-------");
        System.out.println("ID       名称        GP");
        for (Subject sub : subjectCage) {
            System.out.println(sub.ID + "        " + sub.name + "        " + sub.GP);
        }
    }
}
