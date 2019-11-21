package org.zhenchao.builder;

import org.zhenchao.builder.v2.MessageBuilder;
import org.zhenchao.builder.v3.Message;

/**
 * 客户端
 *
 * @author zhenchao.wang 2016-10-19 22:02
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        MessageDirector smsDirector = new MessageDirector(new SmsBuilder());
        org.zhenchao.builder.Message sms = smsDirector.build("18888888888", "", "This is a short message!", "", "18511888811");
        System.out.println(sms);

        MessageDirector emailDirector = new MessageDirector(new EmailBuilder());
        org.zhenchao.builder.Message email = emailDirector.build(
                "zhenchao.wang@gmail.com", "builder pattern", "This is an email for you!", "x.jpg", "donna.z@gmail.com");
        System.out.println(email);

        org.zhenchao.builder.v2.Message email2 = new MessageBuilder()
                .setReceiver("18888888888")
                .setBody("This is an email for you!")
                .setSubject("builder pattern")
                .setAttachment("hello.doc")
                .setSender("18511888811")
                .build();
        System.out.println(email2);

        Message mms = Message.newBuilder()
                .setReceiver("18888888888")
                .setBody("This is a multimedia message!")
                .setSubject("happy birthday")
                .setAttachment("birthday song.mp3")
                .setSender("18511888811")
                .build();
        System.out.println(mms);
    }

}
