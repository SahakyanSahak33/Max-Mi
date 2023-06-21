package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final VerificationService verificationService;

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sahakyansahak303@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);
        System.out.println("Mail Sent Successfully ...");
    }

    @Override
    public String sendVerificationCode(String toEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sahakyansahak303@gmail.com");
        mailMessage.setTo(toEmail);
        String code = verificationService.createVerificationCode();
        mailMessage.setText(code);
        mailMessage.setSubject("Verification Code !");
        mailSender.send(mailMessage);
        System.out.println("Mail Sent Successfully ...");
        return code;
    }

}
