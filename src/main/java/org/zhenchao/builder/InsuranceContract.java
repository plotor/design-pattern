package org.zhenchao.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 保险合同
 *
 * @author zhenchao.wang 2016-10-19 21:39
 * @version 1.0.0
 */
public class InsuranceContract {

    private long id;

    private String personName;

    private String companyName;

    private long startDate;

    private long endDate;

    private String other;

    InsuranceContract(InsuranceContractBuilder builder) {
        this.id = builder.getId();
        this.personName = builder.getPersonName();
        this.companyName = builder.getCompanyName();
        this.startDate = builder.getStartDate();
        this.endDate = builder.getEndDate();
        this.other = builder.getOther();
    }

    /**
     * 打印详情
     */
    public void display() {
        System.out.println(ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE));
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
