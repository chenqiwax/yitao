package cn.et.yitao.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 需要引用 以下jar包:
 * <dependency>
 * <groupId>javax.mail</groupId>
 * <artifactId>mail</artifactId>
 * <version>1.4.7</version>
 * </dependency>
 * author: pyj
 * time: 2018-10-08 10:59:32
 */
public class QQEmailUtils {

    /**
     * 功能描述 发送邮件
     *
     * @param toEmail // 收件人的邮箱
     * @param content // 邮件内容
     * @return java.lang.String
     * @author pyj
     * @date 2018/10/9 0009
     */
    public static String sendEmail(String toEmail, String content) {
        try {
            //创建Properties 类用于记录邮箱的一些属性
            final Properties props = new Properties();
            //表示SMTP发送邮件，必须进行身份验证
            props.put("mail.smtp.auth", "true");
            //此处填写SMTP服务器
            props.put("mail.smtp.host", "smtp.qq.com");
            //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
            props.put("mail.smtp.port", "587");
            //此处填写你的账号F
            props.put("mail.user", "3365669963@qq.com");
            //此处的密码就是前面说的16位STMP口令
            props.put("mail.password", "uurundmwwooxdaab");
            //构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            //使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            //创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            //设置发件人
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);

            //设置收件人的邮箱
            InternetAddress to = new InternetAddress(toEmail);
            message.setRecipient(Message.RecipientType.TO, to);

            //设置邮件标题
            message.setSubject("来自[易淘]通知邮件~~~~");
            //设置邮件的内容体
            message.setContent("您的邮箱验证码为: " + content + "   (请妥善保管您的验证码,请勿告知他人!!!)", "text/html;charset=UTF-8");

            //发送邮件
            Transport.send(message);
            return "邮件发送成功";
        } catch (AddressException e) {
            return "邮件发送异常" + e.getMessage();
        } catch (MessagingException e) {
            return "邮件发送异常" + e.getMessage();
        }
    }
}
