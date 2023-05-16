package sahak.sahakyan.maxmi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
public class PasswordDTO {
    String oldPassword;
    String newPassword;
}
