package com.im.model;

import com.im.Utils.MailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("all")
@ConfigurationProperties(prefix = "mail")
@PropertySource(value = "classpath:mail.yml")
public class MailConfig {


    public static String mailAddress;// = "smtp.163.com";


    public static String fromUser;// = "immanage@163.com";


    public static String password;// = "dd980130";

    @Value("${mailAddress}")
    public void setMailAddress(String mailAddress) {
        MailConfig.mailAddress = mailAddress;
    }

    @Value("${fromUser}")
    public void setFromUser(String fromUser) {
        MailConfig.fromUser = fromUser;
    }

    @Value("${password}")
    public void setPassword(String password) {
        MailConfig.password = password;
    }
}
