package com.hern.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @program: springboot-mail
 * @description: MailService
 * @author: 宋兆恒-2336909208@q.com
 * @create: 2020-01-20 14:30
 **/
@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //指定发送者
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sayHello(){
        System.out.println("Hello World");
    }

    /*
    * to：发送给谁
    * subject：发送的主题（邮件主题）
    * content：发送的内容
    * */
    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(from);

        //发送邮件
        try {
            mailSender.send(simpleMailMessage);
            logger.info("简单邮件发送成功");
        } catch (MailException e) {
            logger.error("简单邮件发送失败",e);
        }
    }

}
