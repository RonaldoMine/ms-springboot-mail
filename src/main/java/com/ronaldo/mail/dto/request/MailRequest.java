package com.ronaldo.mail.dto.request;

import lombok.Data;

@Data
public class MailRequest {
    /**
     * to : an email address who could receive a mail
     * subject :  a title of mail
     * content: a body of mail
     */
    String to, subject, content;
}
