package com.fleitao.emailservice.application;

import com.fleitao.emailservice.adapters.EmailSenderGateway;
import com.fleitao.emailservice.core.cases.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        emailSenderGateway.sendEmail(toEmail, subject, body);
    }
}