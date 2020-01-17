import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Manager {
    public static boolean flag = false;
    private static Set<Student> studentCage = new TreeSet<>();
    private static Set<Subject> subjectCage = new TreeSet<>();

    public static void main(String[] args) {
        Menu.show();
        DbUtil.initialMySQL();
        while (true) {
            switch (Menu.choose()) {
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
                    Scanner input = new Scanner(System.in);
                    System.out.print("---请输入查找学生的ID: ");
                    ID = input.nextInt();
                    DbUtil.findStudentList(ID);
            }
            if (flag) {
                break;
            }
        }
    }

    //增加学生信息
    public static void add() {
        int ID;
        String name;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入学生ID: ");
        ID = input.nextInt();
        System.out.print("---请输入学生姓名: ");
        name = input.next();
        Student stu = new Student(ID, name);
        update_new(stu, "语文");
        update_new(stu, "数学");
        update_new(stu, "英语");
        addAPI(stu);
        DbUtil.addStudent(stu);
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
        addAPISub(sub);
        DbUtil.addSubject(sub);
    }

    //供工具类和本类同时使用的添加接口，用于数据同步
    public static void addAPI(Student stu) {
        studentCage.add(stu);
    }
    public static void addAPISub(Subject sub) {
        subjectCage.add(sub);
    }

    //删除学生信息
    public static void delete() {
        int ID;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入删除学生的ID: ");
        ID = input.nextInt();
        Iterator<Student> it = studentCage.iterator();
        while (it.hasNext()) {
            Student stu = it.next();
            if (stu.getID() == ID) {
                studentCage.remove(stu);
                DbUtil.delete(stu);
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("---查无此人");
        }
        flag = false;
    }

    //删除教学科目
    public static void deleteSubject() {
        int ID;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入删除课程的ID: ");
        ID = input.nextInt();
        Iterator<Subject> it = subjectCage.iterator();
        while (it.hasNext()) {
            Subject sub = it.next();
            if (sub.ID == ID) {
                subjectCage.remove(sub);
                DbUtil.deleteSubject(sub);
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("---查无此课");
        }
        flag = false;
    }

    //修改学生成绩
    public static void edit() {
        int ID;
        String name;
        Student stu = null;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入要修改的学生ID: ");
        ID = input.nextInt();
        for (Student temp : studentCage) {
            if (temp.getID() == ID) {
                stu = temp;
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.print("---请输入要修改的科目名称: ");
            name = input.next();
            update(stu, name);
        } else {
            System.out.println("---查无此人!");
        }
        flag = false;
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
        int ID;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入查找学生的ID: ");
        ID = input.nextInt();
        Iterator<Student> it = studentCage.iterator();
        while (it.hasNext()) {
            Student stu = it.next();
            if (stu.getID() == ID) {
                flag = true;
                System.out.println("---ID: " + stu.getID());
                System.out.println("---姓名: " + stu.getName());
                System.out.println("---语文: " + stu.getSubjectScore("语文"));
                System.out.println("---数学: " + stu.getSubjectScore("数学"));
                System.out.println("---英语: " + stu.getSubjectScore("英语"));
                break;
            }
        }
        if (!flag) {
            System.out.println("---查无此人");
        }
        flag = false;
    }

    //查找科目信息
    public  static void searchSubject() {
        int ID;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入查找课程的ID: ");
        ID = input.nextInt();
        Iterator<Subject> it = subjectCage.iterator();
        while (it.hasNext()) {
            Subject sub = it.next();
            if (sub.ID == ID) {
                flag = true;
                System.out.println("---ID: " + sub.ID);
                System.out.println("---名称: " + sub.name);
                System.out.println("---GP: " + sub.GP);
                break;
            }
        }
        if (!flag) {
            System.out.println("---查无此课");
        }
        flag = false;
    }

    //全部学生信息--懒得用printf的显示全部学生信息
    public static void display() {
        System.out.println("-------学生信息表--------");
        System.out.println("ID  姓名  语文  数学  英语");
        for (Student stu : studentCage) {
            System.out.println(stu.getID() + "号  " +
                    stu.getName() + "  " + stu.getSubjectScore("语文") + "  " +
                    stu.getSubjectScore("数学") + "  " + stu.getSubjectScore("英语"));
        }
    }

    //全部科目信息
    private static void displaySubject() {
        System.out.println("-------科目信息表-------");
        System.out.println("ID       名称        GP");
        for ( Subject sub : subjectCage ) {
            System.out.println(sub.ID + "        " + sub.name + "        " + sub.GP);
        }
    }
}