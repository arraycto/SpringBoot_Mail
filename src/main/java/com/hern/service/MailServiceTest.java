package com.hern.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;

/**
 * @program: springboot-mail
 * @description: 测试MailService
 * @author: 宋兆恒-2336909208@q.com
 * @create: 2020-01-20 15:41
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    //测试简单文本文件
    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("13176567194@163.com","Springboot-Mail测试","基于SpringBoot的邮件系统的文本邮件");
    }

    //发送HTML邮件
    @Test
    public void sendHtmlMail() throws MessagingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>\n").append("<body>\n").append("<h1>HTML邮件系统测试</h1>\n").append("</body>\n").append("</html>\n");
        //String content = "<html>\n"+"<body>\n"+"<h1>HTML邮件系统测试</h1>\n"+"</body>\n"+"</html>\n";
        mailService.sendHtmlMail("13176567194@163.com","HTML邮件测试", String.valueOf(stringBuilder));
    }

    //发送单个附件邮件
    @Test
    public void sendAttachmentsMail() throws MessagingException {

        String filePath = "src/main/resources/templates/2019.12杨寨工资明细.xls";

        mailService.sendAttachmentsMail("13176567194@163.com","多个附件邮件测试","附件邮件",filePath);
    }

    //发送多个附件邮件
    @Test
    public void sendAttachmentsMails() throws MessagingException {

        String filePath = "src/main/resources/templates/2019.12杨寨工资明细.xls";
        String filePath2 = "src/main/resources/templates/2019.12杨寨工资明细2.xls";

        ArrayList<String> filePaths = new ArrayList();
        filePaths.add(filePath);
        filePaths.add(filePath2);

        mailService.sendAttachmentsMails("13176567194@163.com","多个附件邮件测试","附件邮件",filePaths);
    }

    @Test
    public void sendImage() throws MessagingException {

        String imagePath = "src/main/resources/templates/ZoomOut_ZH-CN4471982075_1920x1080.jpg";
        String imageId = "123";
        StringBuilder content = new StringBuilder();
        content.append("<html>\n").append("<body>\n").append("<img src=\"").append(imagePath).append("\">\n").append("</body>\n").append("</html>");
        mailService.sendImage("13176567194@163.com","图片邮件","图片邮件",imagePath,imageId);

    }

    @Test
    public void templateMail() throws MessagingException {
        Context context = new Context();
        context.setVariable("id","123");

        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("13176567194@163.com","模板邮件",emailContent);
    }
}
