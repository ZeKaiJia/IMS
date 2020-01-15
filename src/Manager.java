import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Manager {
    public static boolean flag = false;
    private static Set<Student> studentCage = new TreeSet<>();
    private static Subject[] subject = new Subject[3];

    public static void main(String[] args) {
        Menu.show();
        initialSubject();
        while (true) {
            switch (Menu.choose()) {
                //增加
                case 1:
                    add();
                    break;
                //删除
                case 2:
                    delete();
                    break;
                //修改
                case 3:
                    edit();
                    break;
                //查找
                case 4:
                    search();
                    break;
                //显示
                case 5:
                    display();
                    break;
                //科目
                case 6:
                    checkOutSubject();
                    break;
                //关闭
                case 7:
                    flag = true;
                    break;
                case 8:
                    DbUtil.addSubject(subject[0]);
                    DbUtil.addSubject(subject[0]);
                    DbUtil.addSubject(subject[0]);
                    break;
                case 9:
                    int ID;
                    Scanner input = new Scanner(System.in);
                    System.out.print("---请输入查找学生的ID: ");
                    ID = input.nextInt();
                    DbUtil.findStudentList(ID);
            }
            if ( flag ) {
                break;
            }
        }
    }

    public static void initialSubject() {
        Subject Chinese = new Subject(1,"语文",5);
        Subject Maths = new Subject(2,"数学",4);
        Subject English = new Subject(3,"英语",3);
        subject[0] = Chinese;
        subject[1] = Maths;
        subject[2] = English;
    }

    public static void add() {
        int ID;
        String name;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入学生ID: ");
        ID = input.nextInt();
        System.out.print("---请输入学生姓名: ");
        name = input.next();
        Student stu = new Student(ID, name);
        update_new(stu,"语文");
        update_new(stu,"数学");
        update_new(stu,"英语");
        studentCage.add(stu);
        DbUtil.addStudent(stu);
    }

    //懒得用printf的显示全部学生信息
    public static void display() {
        System.out.println("-------学生信息表--------");
        System.out.println("ID  姓名  语文  数学  英语");
        for (Student stu : studentCage) {
            System.out.println(stu.getID() + "号  " +
                    stu.getName() + "  " + stu.getSubjectScore("语文") + "  " +
                    stu.getSubjectScore("数学") + "  " + stu.getSubjectScore("英语"));
        }
    }

    //修改成绩
    public static void edit() {
        int ID;
        String name;
        Student stu = null;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入要修改的学生ID: ");
        ID = input.nextInt();
        for ( Student temp : studentCage ) {
            if ( temp.getID() == ID ) {
                stu = temp;
                flag = true;
                break;
            }
        }
        if ( flag ) {
            System.out.print("---请输入要修改的科目名称: ");
            name = input.next();
            update(stu,name);
        }
        else {
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

    //删除
    public static void delete() {
        int ID;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入删除学生的ID: ");
        ID = input.nextInt();
        Iterator<Student> it = studentCage.iterator();
        while ( it.hasNext() ) {
            Student stu = it.next();
            if ( stu.getID() == ID ) {
                studentCage.remove(stu);
                DbUtil.delete(stu);
                flag = true;
                break;
            }
        }
        if ( !flag ) {
            System.out.println("---查无此人");
        }
        flag = false;
    }

    //查找
    public static void search() {
        int ID;
        Scanner input = new Scanner(System.in);
        System.out.print("---请输入查找学生的ID: ");
        ID = input.nextInt();
        Iterator<Student> it = studentCage.iterator();
        while ( it.hasNext() ) {
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
        if ( !flag ) {
            System.out.println("---查无此人");
        }
        flag = false;
    }

    //查看科目信息
    private static void checkOutSubject() {
        System.out.println("-------科目信息表-------");
        System.out.println("ID       名称        GP");
        System.out.println(subject[0].ID + "        " + subject[0].name + "        " + subject[0].GP);
        System.out.println(subject[1].ID + "        " + subject[1].name + "        " + subject[1].GP);
        System.out.println(subject[2].ID + "        " + subject[2].name + "        " + subject[2].GP);
    }
}
