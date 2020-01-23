package cn.kevin.sms.controller;

/**
 * @author kevin
 */
public class ManagerController {
    public static int dataChoice;
    public static boolean flag = true;

    public static void main(String[] args) throws Exception {
        //第一个初始化的选项提供三个不同数据库的方法入口
        dataChoice = MenuController.initialMenu();
        databaseMethods();
        System.out.println("--- Bye Bye!");
    }

    public static void databaseMethods() throws Exception {
        while ( flag ) {
            switch (dataChoice) {
                case 1:
                    //进入学生数据库操作
                    studentMethods();
                    break;
                case 2:
                    //进入课程数据库操作
                    subjectMethods();
                    break;
                case 3:
                    //进入成绩数据库操作
                    scoreMethods();
                    break;
                default:
                    //错误的选择
                    MenuController.unCorrect();
            }
            //重复功能
            if (flag = MenuController.reRun()) {
                MenuController.reChoose();
            }
        }
    }

    public static void studentMethods() throws Exception {
        AddController addController = new AddController();
        //38,21,1999-10-03,jiazekai1003@gmail.com,1,贾泽楷
        addController.doBiz(1);
    }

    public static void subjectMethods() {

    }

    public static void scoreMethods() {

    }
}
