package com.poly.Lab8.service;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    JavaMailSender mailSender;

    List<Mail> queue = new ArrayList<>();

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }

    @Override
    public void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1 From
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2 To, CC, BCC
            helper.setTo(mail.getTo());
            if (!isNullOrEmpty(mail.getCc())) helper.setCc(mail.getCc());
            if (!isNullOrEmpty(mail.getBcc())) helper.setBcc(mail.getBcc());

            // 2.3 Subject, Body
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            // 2.4 Attachments
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void push(Mail mail) {
        queue.add(mail);
    }

    @Scheduled(fixedDelay = 500)
    public void run() {
        while (!queue.isEmpty()) {
            try {
                this.send(queue.remove(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
