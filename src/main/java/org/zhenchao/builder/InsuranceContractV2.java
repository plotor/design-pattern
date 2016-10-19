package org.zhenchao.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 保险合同改进版
 *
 * @author zhenchao.wang 2016-10-19 21:39
 * @version 1.0.0
 */
public class InsuranceContractV2 {

    public static class InsuranceContractBuilder {

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

        public InsuranceContractV2 build() {
            return new InsuranceContractV2(this);
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

    private long id;

    private String personName;

    private String companyName;

    private long startDate;

    private long endDate;

    private String other;

    private InsuranceContractV2(InsuranceContractBuilder builder) {
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
