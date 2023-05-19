package sahak.sahakyan.maxmi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sahak.sahakyan.maxmi.validator.CheckEmail;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ToString
@Builder
public class DashboardDTO {
    @Size(min = 3, max = 20)
    String username;
    @Size(min = 3, max = 20)
    @NotBlank(message = "This input cannot be empty.")
    String firstName;
    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    String lastName;
    @NotBlank
    @CheckEmail
    String email;
    @NotBlank
    @Pattern(regexp = "\\+\\d{3}-\\d{2}-\\d{3}-\\d{3}", message = "Please use pattern +XXX-XX-XXX-XXX")
    String phoneNumber;
}
