package sahak.sahakyan.maxmi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
public class DashboardDTO {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String username;
}
