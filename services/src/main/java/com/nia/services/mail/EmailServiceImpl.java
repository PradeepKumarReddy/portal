package com.nia.services.mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailServiceImpl {
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
    private SpringTemplateEngine templateEngine;
 
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }

    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("welcome", context);

        //helper.setTo(mail.getTo());
        
        List<String> emails = mail.getMultipleRecipients();
    	String csvEmails = String.join(",", emails);
    	message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(csvEmails));
    	
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        helper.addAttachment("instructions.pdf", new ClassPathResource("/docs/instructions.pdf"));
        
        emailSender.send(message);
    }
    
    public void sendResetPasswordEmail(Mail mail) throws MessagingException, IOException {
    	MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("reset-password", context);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
    
    public void sendResourceUploadEmail(Mail mail) throws MessagingException, IOException {
    	MimeMessage message = emailSender.createMimeMessage();
    	List<String> emails = mail.getMultipleRecipients();
    	String csvEmails = String.join(",", emails);
    	message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(csvEmails));
    	
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("resource_notification", context);
        
        // helper.setTo();
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
    
    public void sendUserActivatedEmail(Mail mail) throws MessagingException, IOException {
    	MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("user_enable", context);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);   
    }
}
