package org.zhenchao.prototype;

/**
 * 复制机
 *
 * @author zhenchao.wang 2016-10-30 10:50
 * @version 1.0.0
 */
public class CloneClient {

    public static void main(String[] args) {
        CloneClient client = new CloneClient();

        Person person = new Person(0, 6);
        person.setName("张三");
        System.out.println(person);
        System.out.println("clone:" + person.clone());

        Sheep sheep = new Sheep(1, 2, "white");
        System.out.println(sheep);
        System.out.println("clone:" + sheep.clone());
    }

}
