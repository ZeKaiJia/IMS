package cn.kevin.sms.test;

import cn.kevin.sms.controller.AddController;

public class FinalTest {
    public static void main(String[] args) throws Exception {
        AddController addController = new AddController();
        //38,21,1999-10-03,jiazekai1003@gmail.com,1,贾泽楷
        addController.doBiz();
    }
}
