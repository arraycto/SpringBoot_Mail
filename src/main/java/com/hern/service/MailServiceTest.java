package com.hern.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("13176567194@163.com","Springboot-Mail测试","基于SpringBoot的邮件系统的文本邮件");
    }
}
