package sahak.sahakyan.maxmi.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
    String sendVerificationCode(String toEmail);
}
