import java.util.Scanner;

public class Menu {
    public static void show() {
        System.out.println("----------------------------------");
        System.out.println("-------欢迎来到学生成绩管理系统-------");
        System.out.println("-----------1.增加学生信息-----------");
        System.out.println("-----------2.删除学生信息-----------");
        System.out.println("-----------3.修改学生信息-----------");
        System.out.println("-----------4.查找学生信息-----------");
        System.out.println("-----------5.全部学生信息-----------");
        System.out.println("-----------6.查看科目信息-----------");
        System.out.println("-----------7.退出管理系统-----------");
        System.out.println("----------------------------------");
        System.out.println("-----------数据库功能选择-----------");
        System.out.println("-----------8.初始化课程信息---------");
        System.out.println("-----------9.从数据库中查找---------");
    }
    public static int choose() {
        System.out.print("-->请选择功能: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
