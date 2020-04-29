/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.service;

import com.junkie.dto.SenderDTO;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ashish.yetre
 */
public class EmailSenderService implements ISenderService {

    //https://myaccount.google.com/lesssecureapps?pli=1
    private String username="@gmail.com";
    private String password="";
    @Override
    public void send(SenderDTO senderDTO) {
        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "587");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("artificialgenius.ashyz@gmail.com"));
            message.setSubject(senderDTO.getEmailSubject());
            message.setText(senderDTO.getEmailMessage());
            Transport.send(message);
            System.out.println("Email Send successfully.");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
