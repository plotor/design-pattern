package org.zhenchao.chain.model;

/**
 * 函数结果
 *
 * @author zhenchao.wang 2016-09-11 16:50
 * @version 1.0.0
 */
public class Result {

    private String valA;

    private String valB;

    private String valC;

    public Result() {
    }

    public Result(String valA, String valB, String valC) {
        this.valA = valA;
        this.valB = valB;
        this.valC = valC;
    }

    @Override
    public String toString() {
        return "Result{" +
                "valA='" + valA + '\'' +
                ", valB='" + valB + '\'' +
                ", valC='" + valC + '\'' +
                '}';
    }

    public String getValA() {
        return valA;
    }

    public void setValA(String valA) {
        this.valA = valA;
    }

    public String getValB() {
        return valB;
    }

    public void setValB(String valB) {
        this.valB = valB;
    }

    public String getValC() {
        return valC;
    }

    public void setValC(String valC) {
        this.valC = valC;
    }
}
