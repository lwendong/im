package com.im.Utils;

import com.im.model.MailConfig;
import com.im.model.MailInfo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    public static final String TITLE = "iM";

    public static final String CONTENT_PREFIX = "【iM】 ";


    public static boolean sendMail(MailInfo mailInfo){

        Properties pro = new Properties();
        //设置发送邮件 邮箱服务器 默认是 163 服务器
        pro.setProperty("mail.host",MailConfig.mailAddress);
        // 设置通信协议
        pro.setProperty("mail.transport.protocol", "smtp");
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        pro.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(pro);
        session.setDebug(true);
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(MailConfig.mailAddress,25, MailConfig.fromUser, MailConfig.password);
            MimeMessage mimeMessage = createMail(session,mailInfo);
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(transport != null){
                    transport.close();
                }
            } catch (MessagingException e) {

            }
        }
        return true;
    }

    public static MimeMessage createMail(Session session,MailInfo mailInfo) throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(MailConfig.fromUser));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.CC,new InternetAddress(MailConfig.fromUser));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(mailInfo.getToMailAccount()));
        //邮件的标题
        message.setSubject(mailInfo.getTitle());
        //邮件的文本内容
        message.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}
