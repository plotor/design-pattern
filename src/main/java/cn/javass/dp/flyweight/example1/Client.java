package cn.javass.dp.flyweight.example1;

public class Client {

    public static void main(String[] args) {
        //需要先登录，然后再判断是否有权限
        SecurityManager se = SecurityManager.getInstance();
        se.login("张三");
        se.login("李四");
        boolean f1 = se.hasPermit("张三", "薪资数据", "查看");
        boolean f2 = se.hasPermit("李四", "薪资数据", "查看");

        System.out.println("f1==" + f1);
        System.out.println("f2==" + f2);

        for (int i = 0; i < 3; i++) {
            se.login("张三" + i);
            se.hasPermit("张三" + i, "薪资数据", "查看");
        }
    }

}
