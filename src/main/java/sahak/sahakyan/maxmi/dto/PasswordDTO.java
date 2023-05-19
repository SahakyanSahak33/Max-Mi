package sahak.sahakyan.maxmi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sahak.sahakyan.maxmi.validator.Password;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@ToString
@Data
public class PasswordDTO {
    String oldPassword;
    @NotBlank
    @Password
    String newPassword;
}
