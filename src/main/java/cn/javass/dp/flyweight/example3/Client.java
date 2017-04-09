package cn.javass.dp.flyweight.example3;

public class Client {

    public static void main(String[] args) throws Exception {
        //需要先登录，然后再判断是否有权限
        SecurityManager securityManager = SecurityManager.getInstance();
        securityManager.login("张三");
        securityManager.login("李四");
        boolean f1 = securityManager.hasPermit("张三", "薪资数据", "查看");
        boolean f2 = securityManager.hasPermit("李四", "薪资数据", "查看");

        System.out.println("f1==" + f1);
        System.out.println("f2==" + f2);

        for (int i = 0; i < 3; i++) {
            securityManager.login("张三" + i);
            securityManager.hasPermit("张三" + i, "薪资数据", "查看");
        }
    }

}