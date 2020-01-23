package cn.kevin.sms.controller;

/**
 * @author kevin
 */
public class ManagerController {
    public static int dataChoice;
    public static int functionChoice;
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
                    methods(1);
                    break;
                case 2:
                    //进入课程数据库操作
                    methods(2);
                    break;
                case 3:
                    //进入成绩数据库操作
                    methods(3);
                    break;
                default:
                    //错误的选择
                    MenuController.unCorrect();
            }
            //重复功能
            if (flag = MenuController.reRun()) {
                dataChoice = MenuController.reChoose();
            }
        }
    }

    public static void methods(int c) throws Exception {
        MenuController.functionMenu();
        functionChoice = MenuController.choice();
        switch (functionChoice) {
            case 1:
                InsertController insertController = new InsertController();
                //38,21,1999-10-03,jiazekai1003@gmail.com,1,贾泽楷
                //6,高数专升本,6,6
                insertController.doBiz(c);
                break;
            case 2:
                DeleteController deleteController = new DeleteController();
                deleteController.doBiz(c);
                break;
            case 3:
                UpdateController updateController = new UpdateController();
                updateController.doBiz(c);
                break;
            case 4:
                SelectController selectController = new SelectController();
                selectController.doBiz(c);
                break;
            default:
                MenuController.unCorrect();
        }
    }
}
