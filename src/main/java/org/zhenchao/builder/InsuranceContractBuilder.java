package org.zhenchao.builder;

/**
 * 保险合同生成器
 *
 * @author zhenchao.wang 2016-10-19 21:42
 * @version 1.0.0
 */
public class InsuranceContractBuilder {

    private long id;

    private String personName;

    private String companyName;

    private long startDate;

    private long endDate;

    private String other;

    public InsuranceContractBuilder(long id, long startDate, long endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public InsuranceContractBuilder setPersonName(String personName) {
        this.personName = personName;
        return this;
    }

    public InsuranceContractBuilder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public InsuranceContractBuilder setOther(String other) {
        this.other = other;
        return this;
    }

    public InsuranceContract build() {
        return new InsuranceContract(this);
    }

    public long getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getOther() {
        return other;
    }
}
