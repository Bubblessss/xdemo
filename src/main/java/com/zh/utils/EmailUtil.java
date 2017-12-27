package com.zh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件工具类
 * @author zhanghang
 * @date 2017/12/19
 */
@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender mailSenderAutowired;

	private static JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String usernameValue;

	private static String username;

	/**
	 * 初始化为静态变量赋值
	 * PostConstruct bean初始化之前调用
	 */
	@PostConstruct
	public void init() {
		mailSender = mailSenderAutowired;
		username = usernameValue;
	}


	public static void sendEmail(String to, String subject, String text){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(username);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}

	public static void sendEmailWithFile(String to, String subject, String text, String fileName,InputStreamSource file) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(username);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		helper.addAttachment(fileName, file);
		mailSender.send(mimeMessage);
	}
}