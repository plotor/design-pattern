package org.zhenchao.prototype;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 羊
 *
 * @author zhenchao.wang 2016-10-30 10:39
 * @version 1.0.0
 */
public class Sheep implements Animal {

    /** 性別 */
    private int gender;

    /** 体重 */
    private int weight;

    /** 毛色 */
    private String color;

    public Sheep(int gender, String color) {
        this.gender = gender;
        this.color = color;
    }

    public Sheep(int gender, int weight, String color) {
        this.gender = gender;
        this.weight = weight;
        this.color = color;
    }

    @Override
    public Animal clone() {
        Sheep sheep = new Sheep(this.getGender(), this.getColor());
        sheep.setWeight(0);
        return sheep;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
