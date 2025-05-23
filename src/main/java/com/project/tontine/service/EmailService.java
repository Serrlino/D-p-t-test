package com.project.tontine.service;

import com.project.tontine.model.Activation;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@AllArgsConstructor
@Service
public class EmailService 
{
    private JavaMailSender javaMailSender;

    public void sendActivationCodeEmail(Activation activation)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("yanntchouanguem@gmail.com");
        mailMessage.setTo(activation.getMember().getEmail());
        mailMessage.setReplyTo("yanntchouanguem@gmail.com");

        mailMessage.setSubject("Validation");

        String message = String.format("Votre code d'activation est : %s", activation.getCode());
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
}
