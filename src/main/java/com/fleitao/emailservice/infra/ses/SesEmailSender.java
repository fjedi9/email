package com.fleitao.emailservice.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fleitao.emailservice.adapters.EmailSenderGateway;
import com.fleitao.emailservice.core.exceptions.EmailServiceException;
@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService sesClient;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService sesClient) {
        this.sesClient = sesClient;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("vieira.felipe@gmail.com")
                .withDestination(new Destination().withToAddresses(toEmail))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );

        try {
            sesClient.sendEmail(request);
        } catch (AmazonServiceException ex) {
            throw new EmailServiceException("Email sending failed", ex);
        }
    }
}