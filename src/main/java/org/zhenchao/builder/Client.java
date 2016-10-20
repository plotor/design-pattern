package org.zhenchao.builder;

import java.util.Calendar;

/**
 * 客户端
 *
 * @author zhenchao.wang 2016-10-19 22:02
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {

        MessageDirector smsDirector = new MessageDirector(new ShortMessageBuilder());
        Message sms = smsDirector.build("18888888888", "", "This is a short message!", "", "18511888811");
        System.out.println(sms);

        MessageDirector emailDirector = new MessageDirector(new EmailBuilder());
        Message email = emailDirector.build("zhenchao.wang@gmail.com", "builder pattern", "This is an email for you!", "x.jpg", "donna.z@gmail.com");
        System.out.println(email);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long startDate = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, 1);
        long endDate = calendar.getTimeInMillis();

        MultimediaMessageBuilder builder = new MultimediaMessageBuilder(10001L, startDate, endDate);
        // 客户端同时兼指导者
        InsuranceContract personalContract = builder.setPersonName("zhenchao").setOther("balabalabala~").build();
        personalContract.display();

        MultimediaMessageBuilder builder2 = new MultimediaMessageBuilder(20001L, startDate, endDate);
        // 客户端同时兼指导者
        InsuranceContract companyContract = builder2.setPersonName("xiaomi").setOther("balabalabala~").build();
        companyContract.display();

        InsuranceContractV2.InsuranceContractBuilder builder3 = new InsuranceContractV2.InsuranceContractBuilder(30001L, startDate, endDate);
        // 客户端同时兼指导者
        InsuranceContractV2 personalContract2 = builder3.setPersonName("xiaochao").setOther("balabalabala~").build();
        personalContract2.display();

    }

}
