import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Manager {
    public static boolean flag = false;
    private static Set<Student> studentCage = new TreeSet<>();

    public static void main(String[] args) {
        Menu.show();
        while (true) {
            switch (Menu.choose()) {
                //增加
                case 1:
                    add();
                    break;
                //删除
                case 2:
                    break;
                //修改
                case 3:
                    break;
                //查找
                case 4:
                    break;
                //显示
                case 5:
                    display();
                    break;
                //科目
                case 6:
                    break;
                //关闭
                case 7:
                    flag = true;
                    break;
            }
            if ( flag ) {
                break;
            }
        }
    }

    public static void add() {
        int ID;
        String name;
        Scanner input = new Scanner(System.in);
        System.out.println("---请输入学生ID: ");
        ID = input.nextInt();
        System.out.println("---请输入学生姓名: ");
        name = input.next();
        Student stu = new Student(ID, name);
        studentCage.add(stu);
    }

    //懒得用printf的显示全部学生信息
    public static void display() {
        System.out.println("-------学生信息表--------");
        System.out.println("ID  姓名  语文  数学  英语");
        for (Student stu : studentCage) {
            System.out.println(stu.getID() + "号  " +
                    stu.getName() + "  " + stu.getSubject().get("语文") + "  " +
                    stu.getSubject().get("数学") + "  " + stu.getSubject().get("英语"));
        }
    }
}
