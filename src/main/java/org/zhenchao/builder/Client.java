package org.zhenchao.builder;

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

        MessageBuilderV2 builderV2 = new MessageBuilderV2("18888888888", "This is an email for you!", "18511888811");
        MessageV2 email2 = builderV2.setSubject("builder pattern").setAttachment("hello.doc").build();
        System.out.println(email2);

        MessageV3 mms = new MessageV3.MessageBuilder("18888888888", "This is a multimedia message!", "18511888811")
                .setSubject("happy birthday").setAttachment("birthday song.mp3").build();
        System.out.println(mms);
    }

}
