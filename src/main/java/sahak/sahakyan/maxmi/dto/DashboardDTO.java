package sahak.sahakyan.maxmi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
public class DashboardDTO {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String username;
}
