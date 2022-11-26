package com.ronaldo.mail.controller;

import com.ronaldo.mail.dto.request.MailRequest;
import com.ronaldo.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/")
    public String index() {
        return "Microservice To send Mail to end user :)";
    }

    /**
     * Route to send a simple mail
     * @param mailRequest required the parameters to, subject, content
     * @return {
     *     message: Message of a result
     * }
     */
    @PostMapping("api/simple-mail")
    public ResponseEntity<?> simpleMail(@RequestBody MailRequest mailRequest) {
        return mailService.sendSimpleMail(mailRequest);
    }

}
