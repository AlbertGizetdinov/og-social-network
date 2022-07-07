package ru.itis.og.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.og.exception.OgServiceException;
import ru.itis.og.model.Account;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {
    private final FreeMarkerConfigurer freeMarkerConfigurer;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendConfirmMail(String to, String subject, String templateName, Account account) {
        try {
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("first_name", account.getFirstname());
            templateData.put("last_name", account.getLastname());
            templateData.put("confirm_code", account.getConfirmCode());
            templateData.put("email", account.getEmail());

            String templateContent = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfigurer.getConfiguration().getTemplate(templateName), templateData
            );
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject(subject);
                messageHelper.setText(templateContent, true);
                messageHelper.setTo(to);
                messageHelper.setFrom(from);
            };

            new Thread(() -> mailSender.send(preparator)).start();
        } catch (Exception e) {
            throw new OgServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
