package sahak.sahakyan.maxmi.service;

public interface VerificationService {
    String createVerificationCode();
    boolean checkVerificationCode(String code, String inputCode);
}
