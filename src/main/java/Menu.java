import java.util.Scanner;

public class Menu {
    public static void show() {
        System.out.println("----------------------------------");
        System.out.println("-------欢迎来到学生成绩管理系统-------");
        System.out.println("-----------1.增加学生信息-----------");
        System.out.println("-----------2.删除学生信息-----------");
        System.out.println("-----------3.修改学生成绩-----------");
        System.out.println("-----------4.查找学生信息-----------");
        System.out.println("-----------5.全部学生信息-----------");
        System.out.println("-----------6.增加教学科目-----------");
        System.out.println("-----------7.删除教学科目-----------");
        System.out.println("-----------8.全部科目信息-----------");
        System.out.println("-----------9.查找科目信息-----------");
        System.out.println("-----------10.退出管理系统-----------");
        System.out.println("----------------------------------");
        System.out.println("-----------数据库功能选择-----------");//调试用
        System.out.println("---------11.从数据库中查找学生-------");
        System.out.println("---------12.从数据库中查找课程-------");
    }
    public static int choose() {
        System.out.print("-->请选择功能: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
