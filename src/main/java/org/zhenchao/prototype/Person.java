package org.zhenchao.prototype;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人
 *
 * @author zhenchao.wang 2016-10-30 10:46
 * @version 1.0.0
 */
public class Person implements Animal {

    /** 姓名 */
    private String name;

    /** 年龄 */
    private int age;

    /** 性别 */
    private int gender;

    /** 体重 */
    private int weight;

    public Person(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    public Person(int gender, int weight) {
        this.age = 1;
        this.gender = gender;
        this.weight = weight;
    }

    @Override
    public Animal clone() {
        Person person = new Person(this.getName(), this.getGender());
        person.setAge(1);
        person.setWeight(0);
        return person;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }
}
