package com.ronaldo.mail.service;

import com.ronaldo.mail.dto.request.MailRequest;
import com.ronaldo.mail.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MailService {
    @Value("${spring.mail.host}")
    String host;
    @Value("${spring.mail.port}")
    int port;
    @Value("${spring.mail.password}")
    String password;
    @Value("${spring.mail.username}")
    String username;

    JavaMailSenderImpl javaMailSender;

    /**
     * To initialis a javaMailSender with a configuration set up in applications properties
     */
    @PostConstruct
    public void init() {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPort(port);
        javaMailSender.setPassword(password);
    }

    /**
     * Send a basic mail using values in mailRequest
     *
     * @param mailRequest required the parameters to, subject, content
     * @return {
     *     message: Message of a result in Response Entity
     * }
     */
    public ResponseEntity<?> sendSimpleMail(MailRequest mailRequest) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailRequest.getTo());
            simpleMailMessage.setFrom(username);
            simpleMailMessage.setSubject(mailRequest.getSubject());
            simpleMailMessage.setText(mailRequest.getContent());
            javaMailSender.send(simpleMailMessage);
            return ResponseEntity.ok(new MessageResponse("Your email with subject '" + mailRequest.getSubject() + "' was sent successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }

    }
}
