package org.zhenchao.singleton;

/**
 * @author zhenchao.wang 2016-10-15 21:03
 * @version 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        ChildRegisterSingleton c = ChildRegisterSingleton.getInstance();
        System.out.println(c);
        /*InnerClassSingleton sic = InnerClassSingleton.getInstance();
        System.out.println(sic);*/
    }

}
